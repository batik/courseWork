package com.epam.shop.parsers;

import com.epam.shop.beans.UserBean;
import com.epam.shop.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Util parser for for creating UserBean while registration.
 * <p/>
 * Class for Task9
 * Created by Oleh_Osyka on 22-Oct-14.
 */
public class RegisterUserParser implements Parser, Constants {

    /**
     * Parsing inputted date to UserBean
     *
     * @param request for parsing
     * @return UserProfileBean with parsed information.
     */
    public UserBean parse(HttpServletRequest request) {
        UserBean formBean = new UserBean();
        formBean.setLogin(request.getParameter(PARAMETERS_LOGIN));
        formBean.setName(request.getParameter(PARAMETERS_NAME));
        formBean.setPass(request.getParameter(PARAMETERS_PASS));
        formBean.setEmail(request.getParameter(PARAMETERS_EMAIL));
        formBean.setGender(request.getParameter(PARAMETERS_GENDER));
        formBean.setFeeds(Arrays.asList(request.getParameterValues(PARAMETERS_FEEDS)));
        return formBean;
    }

}
