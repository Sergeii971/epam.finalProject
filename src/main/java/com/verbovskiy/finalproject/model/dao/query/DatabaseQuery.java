package com.verbovskiy.finalproject.model.dao.query;

public class DatabaseQuery {
    private DatabaseQuery() {
    }

    public static final String ADD_ACCOUNT = "INSERT INTO account(login, password, isAdmin, isBlocked, isConfirmed) " +
            "VALUES (?, ?, ?, ?, ?)";
    public static final String REMOVE_ACCOUNT = "DELETE FROM account " + "WHERE login = ?";
    public static final String FIND_ALL_ACCOUNTS = "SELECT login, isAdmin, isBlocked, isConfirmed" + " FROM account";
    public static final String FIND_ACCOUNT_BY_LOGIN = FIND_ALL_ACCOUNTS + " WHERE login = ? ";
    public static final String FIND_ACCOUNT_BY_LOGIN_AND_PASSWORD = FIND_ALL_ACCOUNTS + " WHERE login = ?" +
            " AND password = ?";
    public static final String CHANGE_PASSWORD = "UPDATE account SET password = ? WHERE login = ?";
    public static final String CHANGE_BLOCK_STATUS = "UPDATE account SET isBlocked = ? WHERE login = ?";
    public static final String CHANGE_CONFIRM_STATUS = "UPDATE account SET isConfirmed = ? WHERE login = ?";

    public static final String ADD_USER = "INSERT INTO user(email, name, user_login, surname) " +
            "VALUES (?, ?, ?, ?)";
    public static final String REMOVE_USER = "DELETE FROM user " + "WHERE email = ?";
    public static final String FIND_ALL_USERS = "SELECT login, isAdmin, isBlocked, isConfirmed, email, name, surname" +
            " FROM user Inner Join account ON account.login = user_login";
    public static final String FIND_ALL_BLOCKED_USERS = "SELECT login, isAdmin, isBlocked, isConfirmed, email, name, surname" +
            " FROM user Inner Join account ON account.login = user_login WHERE isBlocked = ?";
    public static final String FIND_ALL_NOT_CONFIRMED_USERS = "SELECT login, isAdmin, isBlocked, isConfirmed, email, name, surname" +
            " FROM user Inner Join account ON account.login = user_login WHERE isConfirmed = ?";
    public static final String FIND_USER_BY_EMAIL = FIND_ALL_USERS + " WHERE email = ?";
    public static final String SEARCH_USER = FIND_ALL_USERS + " WHERE CONCAT(email, name, surname) LIKE concat('%' , ?, '%')";
}