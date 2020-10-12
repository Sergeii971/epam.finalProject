package com.verbovskiy.finalproject.model.dao.account.impl;

import com.verbovskiy.finalproject.exception.DaoException;
import com.verbovskiy.finalproject.model.connection.ConnectionPool;
import com.verbovskiy.finalproject.model.dao.account.AccountDao;
import com.verbovskiy.finalproject.model.dao.query.DatabaseQuery;
import com.verbovskiy.finalproject.model.entity.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoImpl implements AccountDao {
    private static final String LOGIN_COLUMN_NAME = "login";
    private static final String IS_ADMIN_COLUMN_NAME = "isAdmin";
    private static final String IS_BLOCKED_COLUMN_NAME = "isBlocked";

    @Override
    public void add(String login, String password, boolean isAdmin, boolean isBlocked) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(DatabaseQuery.ADD_ACCOUNT)) {
            statement.setString(1, login);
            statement.setString(2, password);
            statement.setBoolean(3, isAdmin);
            statement.setBoolean(4, isBlocked);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error while adding user to database", e);
        }
    }

    @Override
    public void remove(String login) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(DatabaseQuery.REMOVE_ACCOUNT)) {
            statement.setString(1, login);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error while removing user from database", e);
        }
    }

    @Override
    public List<Account> findAll() throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(DatabaseQuery.FIND_ALL_ACCOUNTS)) {
            List<Account> accounts = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Account account = createAccountFromSql(resultSet);
                    accounts.add(account);
                }
            return accounts;
        } catch (SQLException e) {
            throw new DaoException("Error while get all users from database", e);
        }
    }

    @Override
    public void changeUserBlockStatus(String login, boolean status) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(DatabaseQuery.CHANGE_STATUS)) {
            statement.setBoolean(1, status);
            statement.setString(2, login);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error during changing user block status in database", e);
        }
    }

    @Override
    public Account findByLogin(String login) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(DatabaseQuery.FIND_ACCOUNT_BY_LOGIN)) {
            statement.setString(1, login);
            Account account = null;
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                account = createAccountFromSql(resultSet);
            }
            return account;
        } catch (SQLException e) {
            throw new DaoException("Error while finding user by login from database", e);
        }
    }

    @Override
    public Account findByLoginPassword(String login, String password) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(DatabaseQuery.FIND_ACCOUNT_BY_LOGIN_AND_PASSWORD)) {
            statement.setString(1, login);
            statement.setString(2, password);
            Account account = null;
            ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    account = createAccountFromSql(resultSet);
                }
            return account;
        } catch (SQLException e) {
            throw new DaoException("Error while finding user by login and password from database", e);
        }
    }

    public void updateIsBlockedStatus(String login) {

    }

    private Account createAccountFromSql(ResultSet resultSet) throws SQLException {
        String login = resultSet.getString(LOGIN_COLUMN_NAME);
        boolean isAdmin = resultSet.getBoolean(IS_ADMIN_COLUMN_NAME);
        boolean isBlocked = resultSet.getBoolean(IS_BLOCKED_COLUMN_NAME);
        return new Account(login, isAdmin, isBlocked);
    }
}
