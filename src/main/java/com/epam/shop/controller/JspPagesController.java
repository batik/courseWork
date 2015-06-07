package com.epam.shop.controller;

import com.epam.shop.entity.Cart;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

/**
 * @Author Oleh_Osyka on 13.02.2015 for Spring-shop.com.epam.shop.controller.
 */
@Controller
@SessionAttributes({"user", "cart", "userOrderInfo"})
public class JspPagesController {

    @RequestMapping(value = "/checkout/create", method = RequestMethod.GET)
    public String getCheckoutPage(WebRequest request) {
        return "checkout";
    }

    @RequestMapping(value = "/checkout/checkoutPayment/", method = RequestMethod.GET)
    public String getCheckoutPaymentPage() {
        return "checkoutRequisite";
    }

    @RequestMapping(value = "/checkout/checkoutResult/", method = RequestMethod.GET)
    public String getCheckoutResultPage() {
        return "checkoutResult";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String getProfilePage() {
        return "profile";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String getIndexPage() {
        return "index";
    }
}
