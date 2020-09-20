package com.verbovskiy.finalproject.model.validator;

import com.verbovskiy.finalproject.model.entity.Account;

import java.util.List;

public class AccountValidator {
    private final int LINE_SIZE_IN_DATABASE = 130;

    public boolean validateLoginPassword(String login, String password) {
        boolean result = true;
        if ((login.isEmpty()) || (login.length() > LINE_SIZE_IN_DATABASE)
                || (password.isEmpty()) || (password.length() > LINE_SIZE_IN_DATABASE)) {
            result = false;
        }
        return result;
    }

    public boolean isLoginExists(Account newAccount, List<Account> accounts) {
        boolean result = false;
        for (Account account : accounts) {
            if (account.getLogin().equals(newAccount.getLogin())) {
                result = true;
                break;
            }
        }
        return result;
    }
}
