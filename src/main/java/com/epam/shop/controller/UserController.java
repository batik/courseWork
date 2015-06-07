package com.epam.shop.controller;

import com.epam.shop.beans.UserBean;
import com.epam.shop.domain.User;
import com.epam.shop.service.captcha.ICaptchaService;
import com.epam.shop.service.image.IImageService;
import com.epam.shop.service.user.IUserService;
import com.epam.shop.utils.MD5PasswordGenerator;
import com.epam.shop.validation.Validator;
import com.epam.shop.validation.ValidatorRegisterFormBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Oleh_Osyka on 11.02.2015 for Spring-shop.com.epam.shop.controller.
 */
@Controller
@SessionAttributes("user")
public class UserController {

    public static final String PARAMETERS_FILE = "file";
    public static final String PARAMETERS_CAPTCHA = "captcha";
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private static final String PARAMETERS_LAST_PAGE = "viewid";
    private static final String PARAMETERS_LOGIN = "first_name";
    private static final String PARAMETERS_LOGIN_AUTHORIZE = "first_name_login";
    private static final String PARAMETERS_PASS_AUTHORIZE = "pass_login";
    private static final String PARAMETERS_REGISTER_FORM_ERRORS = "rfErrors";
    private static final String PARAMETERS_USER_BEAN = "userBean";
    @Autowired
    private IUserService userService;
    @Autowired
    private IImageService imageService;
    @Autowired
    private ICaptchaService captchaService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(WebRequest request,
                              @RequestParam(PARAMETERS_LOGIN_AUTHORIZE) String login,
                              @RequestParam(PARAMETERS_PASS_AUTHORIZE) String password,
                              @RequestHeader(value = "referer", required = false) String referer) {
        Map<String, String> foundErrors = new HashMap<>();
        int lastIndexOfSlash = referer.lastIndexOf("/");
        referer = referer.substring(lastIndexOfSlash + 1, referer.length()).replace(".jsp", "");
        ModelAndView view = new ModelAndView(referer);
        if (!userService.isLoginExists(login)) {
            foundErrors.put(PARAMETERS_LOGIN_AUTHORIZE, "Login not found");
        }
        User userFromDB = userService.getByLoginAndPass(login, MD5PasswordGenerator.encrypt(password));
        if (userFromDB == null) {
            foundErrors.put(PARAMETERS_PASS_AUTHORIZE, "Login and password not valid!");
        }
        if (foundErrors.isEmpty()) {
            request.setAttribute("user", userFromDB, WebRequest.SCOPE_SESSION);
            view.setViewName("redirect:index");
            return view;
        }
        view.addObject(PARAMETERS_REGISTER_FORM_ERRORS, foundErrors);
        view.addObject(PARAMETERS_USER_BEAN, userFromDB);
        return view;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(WebRequest request, SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        request.removeAttribute("user", WebRequest.SCOPE_SESSION);
        return "redirect:index";
    }

    @RequestMapping(value = "/validLogin", method = RequestMethod.GET)
    public ResponseEntity<String> ajaxLoginValidation(@RequestParam(PARAMETERS_LOGIN) String login) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "text/plain; charset=utf-8");
        return new ResponseEntity<>(String.valueOf(userService.isLoginExists(login)), headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String goToRegisterPage() {
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, headers = "content-type=multipart/form-data")
    public ModelAndView register(WebRequest webRequest,
                                 @ModelAttribute("userBean") UserBean userBean,
                                 @RequestParam(PARAMETERS_FILE) MultipartFile multiPart,
                                 HttpServletRequest request) {

        Validator<UserBean> formBeanValidator = new ValidatorRegisterFormBean();
        Map<String, String> foundErrors = formBeanValidator.validate(userBean);

        if (!foundErrors.containsKey(PARAMETERS_LOGIN) && userService.isLoginExists(userBean.getLogin())) {
            foundErrors.put(PARAMETERS_LOGIN, "Login already exists");
        }
        if (!captchaService.isRight(request)) {
            foundErrors.put(PARAMETERS_CAPTCHA, "Captcha is wrong");
        }

        if (!multiPart.getContentType().equals("image/jpeg")) {
            foundErrors.put(PARAMETERS_FILE, "Avatar should be .jpg/.jpeg");
        }
        if (foundErrors.isEmpty()) {
            User newUser = new User(userBean);
            imageService.saveImage(newUser, multiPart);
            webRequest.setAttribute("user", userService.add(newUser), WebRequest.SCOPE_SESSION);
            return new ModelAndView("index");
        }
        userBean.setPass("");
        ModelAndView view = new ModelAndView("register");
        view.addObject(PARAMETERS_REGISTER_FORM_ERRORS, foundErrors);
        view.addObject(PARAMETERS_USER_BEAN, userBean);
        logger.info("Errors in registration form. Registration denied.");
        return view;
    }

}
