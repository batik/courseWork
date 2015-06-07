package com.epam.shop.controller;

import com.epam.shop.beans.OrderInfoBean;
import com.epam.shop.domain.Item;
import com.epam.shop.domain.User;
import com.epam.shop.entity.Cart;
import com.epam.shop.entity.FilterCriteria;
import com.epam.shop.service.product.ProductService;
import com.epam.shop.service.shop.order.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Oleh_Osyka on 13.02.2015 for Spring-shop.com.epam.shop.controller.
 */
@Controller
@SessionAttributes({"user", "cart", "userOrderInfo"})
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private static final String PARAMETER_PAGE = "page";
    private static final String PARAMETER_ITEM_LIST = "itemList";
    private static final String PARAMETER_TOTAL_PAGE = "total_page_number";
    private static final String PARAMETER_LIST_PER_PAGE = "listPerPage";
    private static final String PRODUCT_FILTER_PARAMETER = "filter";
    private static final int DEFAULT_OFFSET = 1;
    private static final int DEFAULT_SIZE_PER_PAGE = 3;
    private static final String PRODUCT_NAME_PARAMETER = "name";
    private static final String PRODUCT_AUTHOR_PARAMETER = "author";
    private static final String PRODUCT_MIN_YEAR_PARAMETER = "startYear";
    private static final String PRODUCT_MAX_YEAR_PARAMETER = "endYear";
    private static final String PRODUCT_MIN_PRICE_PARAMETER = "startPrice";
    private static final String PRODUCT_MAX_PRICE_PARAMETER = "endPrice";
    private static final String CATEGORY_PARAMETER = "categories";
    private static final String PRODUCT_SORT_TYPE_PARAMETER = "sortType";
    private static final String PARAMETER_USER_CART_INFO = "userOrderInfo";

    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;


    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public String getProductList(HttpServletRequest req) {
        FilterCriteria filterCriteria = getFilterAttributes(req);
        int currentPage = filterCriteria.getPageOffset();
        int listLength = filterCriteria.getListLength();
        filterCriteria.setPageOffset((currentPage - 1) * listLength);
        List<Item> itemList = productService.filter(filterCriteria);
        int countProducts = productService.getCountProducts(filterCriteria);
        int extraPage = (countProducts % listLength) > 0 ? 1 : 0;
        int totalPage = countProducts / listLength + extraPage;
        req.setAttribute(PARAMETER_PAGE, currentPage);
        req.setAttribute(PARAMETER_LIST_PER_PAGE, itemList.size());
        req.setAttribute(PARAMETER_TOTAL_PAGE, totalPage);
        req.setAttribute(PARAMETER_ITEM_LIST, itemList);
        req.setAttribute(PRODUCT_FILTER_PARAMETER, filterCriteria);
        return "shop";
    }

    @RequestMapping(value = "/cart/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Number> addToCart(WebRequest request,
                                         @RequestParam("productId") String productId) {
        Item product = productService.get(productId);
        Cart cart = (Cart) request.getAttribute("cart", WebRequest.SCOPE_SESSION);
        if (cart == null) {
            cart = new Cart();
            request.setAttribute("cart", cart, WebRequest.SCOPE_SESSION);
        }
        cart.add(product);
        Map<String, Number> response = new HashMap<>();
        response.put("count", cart.size());
        response.put("total", cart.totalSum());
        return response;
    }

    @RequestMapping(value = "/checkout/checkoutInfo", method = RequestMethod.GET)
    public String getCartInfo(@ModelAttribute("cart") Cart cart,
                              @ModelAttribute("userOrderInfo") OrderInfoBean userOrderInfo) {
        if (!isDataValid(cart, userOrderInfo)) {
            return "redirect:/checkout/create";
        }
        return "checkoutResult";
    }

    private boolean isDataValid(Cart userCart, OrderInfoBean userOrderInfo) {
        return !(userOrderInfo == null || (userOrderInfo.getAddress() == null) || userOrderInfo.getCardNumber() == null
                || (userCart == null));
    }

    @RequestMapping(value = "/checkout/checkoutInfo", method = RequestMethod.POST)
    public String createCartInfo(WebRequest request,
                                 @RequestParam("cardNumber") String cardNumber,
                                 @RequestParam("address") String address) {
        OrderInfoBean newUserOrderInfo = new OrderInfoBean();
        newUserOrderInfo.setCardNumber(cardNumber);
        newUserOrderInfo.setAddress(address);
        request.setAttribute("userOrderInfo", newUserOrderInfo, WebRequest.SCOPE_SESSION);
        return "redirect:/checkout/checkoutResult/";
    }

    @RequestMapping(value = "/cart/change", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> changeCart(@ModelAttribute("cart") Cart cart,
                                             @RequestParam("productId") String productId,
                                             @RequestParam("productCount") Integer productCount) throws JsonProcessingException {
        if (productCount <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Item product = productService.get(productId);
        cart.set(product, productCount);
        Map<String, Number> response = new HashMap<>();
        response.put("count", productCount);
        response.put("price", product.getPrice());
        response.put("totalLine", product.getPrice() * productCount);
        response.put("total", cart.totalSum());
        return new ResponseEntity<>(new ObjectMapper().writeValueAsString(response), HttpStatus.OK);

    }

    @RequestMapping(value = "/cart/delete", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Map<String, Number> deleteFromCart(@ModelAttribute("cart") Cart cart,
                                              @RequestParam("productId") String productId) {

        Item product = productService.get(productId);
        cart.remove(product);
        Map<String, Number> response = new HashMap<>();
        response.put("total", cart.totalSum());
        return response;
    }

    @RequestMapping(value = "/checkout/buy", method = RequestMethod.POST)
    public ModelAndView makeOrder(@ModelAttribute("userOrderInfo") OrderInfoBean userOrderInfo,
                                  @ModelAttribute("cart") Cart cart,
                                  @ModelAttribute("user") User user) {
        if (!isDataValid(cart, userOrderInfo)) {
            return new ModelAndView("redirect:/checkout/create");
        }
        orderService.makeOrder(cart, userOrderInfo.getAddress(), userOrderInfo.getCardNumber(), user.getId());
        cart.clear();
        userOrderInfo = new OrderInfoBean();
        ModelAndView view = new ModelAndView("checkoutResult");
        view.addObject(PARAMETER_USER_CART_INFO, userOrderInfo);
        view.addObject("successOrder", true);
        return view;

    }

    private FilterCriteria getFilterAttributes(HttpServletRequest request) {
        FilterCriteria filterCriteria = new FilterCriteria();
        String startOffset = request.getParameter(PARAMETER_PAGE);
        if (startOffset != null && !startOffset.isEmpty()) {
            try {
                filterCriteria.setPageOffset(Integer.parseInt(startOffset));
            } catch (NumberFormatException e) {
                logger.warn("Unexpected page values. Set o default", e);
                filterCriteria.setPageOffset(DEFAULT_OFFSET);
            }
        } else {
            filterCriteria.setPageOffset(DEFAULT_OFFSET);
        }
        String listLength = request.getParameter(PARAMETER_LIST_PER_PAGE);
        if (listLength != null && !listLength.isEmpty()) {
            try {
                filterCriteria.setListLength(Integer.parseInt(listLength));
            } catch (NumberFormatException e) {
                logger.warn("Unexpected page values. Set o default", e);
                filterCriteria.setPageOffset(DEFAULT_OFFSET);
            }
        } else {
            filterCriteria.setListLength(DEFAULT_SIZE_PER_PAGE);
        }
        String productName = request.getParameter(PRODUCT_NAME_PARAMETER);
        if (productName != null && !productName.isEmpty()) {
            filterCriteria.setProductName(productName);
        }
        String productAuthor = request.getParameter(PRODUCT_AUTHOR_PARAMETER);
        if (productAuthor != null && !productAuthor.isEmpty()) {
            filterCriteria.setProductAuthor(productAuthor);
        }
        String minYear = request.getParameter(PRODUCT_MIN_YEAR_PARAMETER);
        if (minYear != null && !minYear.isEmpty()) {
            try {
                filterCriteria.setMinYear(Integer.parseInt(minYear));
            } catch (NumberFormatException e) {
                logger.warn("Unexpected min year value.", e);
            }
        }
        String maxYear = request.getParameter(PRODUCT_MAX_YEAR_PARAMETER);
        if (maxYear != null && !maxYear.isEmpty()) {
            try {
                filterCriteria.setMaxYear(Integer.parseInt(maxYear));
            } catch (NumberFormatException e) {
                logger.warn("Unexpected max year value.", e);
            }
        }
        String minPrice = request.getParameter(PRODUCT_MIN_PRICE_PARAMETER);
        if (minPrice != null && !minPrice.isEmpty()) {
            try {
                filterCriteria.setMinPrice(Integer.parseInt(minPrice));
            } catch (NumberFormatException e) {
                logger.warn("Unexpected min price value.", e);
            }
        }
        String maxPrice = request.getParameter(PRODUCT_MAX_PRICE_PARAMETER);
        if (maxPrice != null && !maxPrice.isEmpty()) {
            try {
                filterCriteria.setMaxPrice(Integer.parseInt(maxPrice));
            } catch (NumberFormatException e) {
                logger.warn("Unexpected max price value.", e);
            }
        }
        String sortType = request.getParameter(PRODUCT_SORT_TYPE_PARAMETER);
        if (sortType != null && !sortType.isEmpty()) {
            filterCriteria.setSortType(sortType);
        }
        String[] categories = request.getParameterValues(CATEGORY_PARAMETER);
        if (categories != null) {
            for (String id : categories) {
                if (id.matches("\\d*")) {
                    filterCriteria.addCategorie(Integer.valueOf(id));
                }
            }
        }
        return filterCriteria;
    }

}
