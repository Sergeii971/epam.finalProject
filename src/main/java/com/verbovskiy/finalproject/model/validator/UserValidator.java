package com.verbovskiy.finalproject.model.validator;

import com.verbovskiy.finalproject.model.entity.User;

import java.util.List;

public class UserValidator {
    private final int LINE_SIZE_IN_DATABASE = 32;
    public boolean validateUserData(User user) {
        boolean result = true;
        if (user == null) {
            result = false;
        } else {
            String login = user.getLogin();
            String password = user.getPassword();

            if ((login.isEmpty()) || (login.length() > LINE_SIZE_IN_DATABASE)
                    || (password.isEmpty()) || (password.length() > LINE_SIZE_IN_DATABASE)) {
                result = false;
            }
        }
        return result;
    }

    public boolean isLoginExists(User newUser, List<User> users) {
        boolean result = false;
        for (User user : users) {
            if (user.getLogin().equals(newUser.getLogin())) {
                result = true;
                break;
            }
        }
        return result;
    }
}
