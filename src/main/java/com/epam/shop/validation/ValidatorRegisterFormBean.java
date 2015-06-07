package com.epam.shop.validation;


import com.epam.shop.beans.UserBean;
import com.epam.shop.utils.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Validator implementation for UserBeans
 * <p/>
 * Class for Task9
 * Created by Oleh_Osyka on 22-Oct-14.
 */
public class ValidatorRegisterFormBean implements Validator<UserBean>, Constants {

    private static final String VALIDATOR_INPUTTED_DATA = "^\\w{5,}$";
    private static final int VALIDATOR_MIN_LENGTH = 5;
    private static final String VALIDATOR_PASSWORD = "^[a-zA-Z]\\w{3,14}$";
    private static final String VALIDATOR_EMAIL = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$";

    private Map<String, String> errorsMessageList = new HashMap<>();

    @Override
    public Map<String, String> validate(UserBean formBean) {
        validateLogin(formBean.getLogin());
        validateLastName(formBean.getName());
        validatePass(formBean.getPass());
        validateEmail(formBean.getEmail());
        validateGender(formBean.getGender());
        return errorsMessageList;
    }

    private void validateLogin(String login) {
        if (login == null || "".equals(login.trim())) {
            errorsMessageList.put(PARAMETERS_LOGIN, "Login wasn't input");
            return;
        }
        if (!login.matches(VALIDATOR_INPUTTED_DATA)) {
            errorsMessageList.put(PARAMETERS_LOGIN, "Login less than " + VALIDATOR_MIN_LENGTH);
        }
    }

    private void validateLastName(String lastName) {
        if (lastName == null || "".equals(lastName.trim())) {
            errorsMessageList.put(PARAMETERS_NAME, "Last name wasn't input");
            return;
        }
        if (!lastName.matches(VALIDATOR_INPUTTED_DATA)) {
            errorsMessageList.put(PARAMETERS_NAME, "Last name less than " + VALIDATOR_MIN_LENGTH);
        }
    }

    private void validatePass(String password) {
        if (password == null || "".equals(password.trim())) {
            errorsMessageList.put(PARAMETERS_PASS, "Password wasn't input");
            return;
        }
        if (!password.matches(VALIDATOR_PASSWORD)) {
            errorsMessageList.put(PARAMETERS_PASS, "Password didn't match. Length should be 3-14 symbols and starts with letter.");
        }
    }

    private void validateEmail(String email) {
        if (email == null || "".equals(email.trim())) {
            errorsMessageList.put(PARAMETERS_EMAIL, "Email wasn't input");
            return;
        }
        if (!email.matches(VALIDATOR_EMAIL)) {
            errorsMessageList.put(PARAMETERS_EMAIL, "Email didn't match");
        }
    }

    private void validateGender(String gender) {
        if (gender == null) {
            errorsMessageList.put(PARAMETERS_GENDER, "Sex wasn't matched");
        }
    }

}
