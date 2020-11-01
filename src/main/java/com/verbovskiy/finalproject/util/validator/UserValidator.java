package com.verbovskiy.finalproject.util.validator;

import com.verbovskiy.finalproject.controller.command.RequestParameter;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
    private static final String EMAIL_PATTERN = "^\\p{Alnum}+@+\\p{Alnum}+\\.\\p{Alpha}{2,4}$";
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{8,50}$";
    private static final String NAME_PATTERN = "^[а-яА-Яa-zA-Z-]{1,20}$";

    private UserValidator() {
    }

    public static Map<String, Boolean> validateUserData(String email, String password, String name, String surname) {
        Map<String, Boolean> incorrectParameters = new HashMap<>();
        if (!validateEmail(email)) {
            incorrectParameters.put(RequestParameter.EMAIL, RequestParameter.IS_INCORRECT);
        }
        if (!validatePassword(password)) {
            incorrectParameters.put(RequestParameter.PASSWORD, RequestParameter.IS_INCORRECT);
        }
        if (!validateName(name)) {
            incorrectParameters.put(RequestParameter.NAME, RequestParameter.IS_INCORRECT);
        }
        if (!validateName(surname)) {
            incorrectParameters.put(RequestParameter.SURNAME, RequestParameter.IS_INCORRECT);
        }
        return incorrectParameters;
    }

    public static boolean validateEmail(String email) {
        boolean isEmailCorrect = false;

        if (email != null && !email.isEmpty()) {
            Pattern pattern = Pattern.compile(EMAIL_PATTERN);
            Matcher matcher = pattern.matcher(email);
            isEmailCorrect = matcher.matches();
        }
        return isEmailCorrect;
    }

    public static boolean validatePassword(String password) {
        boolean isPasswordCorrect = false;

        if (password != null && !password.isEmpty()) {
            Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
            Matcher matcher = pattern.matcher(password);
            isPasswordCorrect = matcher.matches();
            }
        return isPasswordCorrect;
    }

    public static boolean validateName(String name) {
        boolean isNameCorrect = false;

        if (name != null && !name.isEmpty()) {
            Pattern pattern = Pattern.compile(NAME_PATTERN);
            Matcher matcher = pattern.matcher(name);
            isNameCorrect = matcher.matches();
        }
        return isNameCorrect;
    }
}
