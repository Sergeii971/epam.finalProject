package com.verbovskiy.finalproject.model.dao.query;

public class DatabaseQuery {
    public static final String ADD_ACCOUNT = "INSERT INTO account(login, password, isAdmin, isBlocked) " +
            "VALUES (?, ?, ?, ?)";
    public static final String REMOVE_ACCOUNT = "DELETE FROM account " + "WHERE login = ?";
    public static final String FIND_ALL_ACCOUNTS = "SELECT login, password, isAdmin, isBlocked" + " FROM account";
    public static final String FIND_ACCOUNT_BY_LOGIN = FIND_ALL_ACCOUNTS + " WHERE login = ? ";
    public static final String FIND_ACCOUNT_BY_LOGIN_AND_PASSWORD = FIND_ALL_ACCOUNTS + " WHERE login = ? " +
            "AND password = ?";

    public static final String ADD_USER = "INSERT INTO user(email, name, user_login, surname) " +
            "VALUES (?, ?, ?, ?)";
    public static final String REMOVE_USER = "DELETE FROM user " + "WHERE email = ?";
    public static final String FIND_ALL_USERS = "SELECT login, isAdmin, isBlocked, email, name, surname" +
            " FROM user Inner Join account ON account.login = user_login";
    public static final String FIND_USER_BY_EMAIL = FIND_ALL_USERS + " WHERE email = ?";
    public static final String CHANGE_STATUS = "UPDATE account SET isBlocked = ? WHERE login = ?";
}
