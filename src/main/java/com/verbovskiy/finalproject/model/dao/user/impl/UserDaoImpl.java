package com.verbovskiy.finalproject.model.dao.user.impl;

import com.verbovskiy.finalproject.exception.DaoException;
import com.verbovskiy.finalproject.model.connection.ConnectionPool;
import com.verbovskiy.finalproject.model.dao.query.DatabaseQuery;
import com.verbovskiy.finalproject.model.dao.user.UserDao;
import com.verbovskiy.finalproject.model.entity.Account;
import com.verbovskiy.finalproject.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static final String LOGIN_COLUMN_NAME = "login";
    private static final String IS_ADMIN_COLUMN_NAME = "isAdmin";
    private static final String IS_BLOCKED_COLUMN_NAME = "isBlocked";
    private static final String EMAIL_COLUMN_NAME = "email";
    private static final String NAME_COLUMN_NAME = "name";
    private static final String SURNAME_COLUMN_NAME = "surname";

    @Override
    public void add(String login, String email, String name, String surname,
                    String encryptedPassword, boolean isAdmin,boolean isBlocked) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();

        try (Connection connection = connectionPool.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement accountStatement = connection.prepareStatement(DatabaseQuery.ADD_ACCOUNT);
                 PreparedStatement userStatement = connection.prepareStatement(DatabaseQuery.ADD_USER)) {
                accountStatement.setString(1, login);
                accountStatement.setString(2, encryptedPassword);
                accountStatement.setBoolean(3, isAdmin);
                accountStatement.setBoolean(4, isBlocked);
                accountStatement.executeUpdate();
                userStatement.setString(1, email);
                userStatement.setString(2, name);
                userStatement.setString(3, login);
                userStatement.setString(4, surname);
                userStatement.executeUpdate();
                connection.commit();
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                connection.rollback();
                connection.setAutoCommit(true);
                throw new SQLException(e);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while adding user to database", e);
        }
    }

    @Override
    public void remove(String email) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(DatabaseQuery.REMOVE_USER)) {
             statement.setString(1, email);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error while removing user from database", e);
        }
    }

    @Override
    public List<User> findAll() throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(DatabaseQuery.FIND_ALL_USERS)) {
            List<User> users = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = createUserFromSql(resultSet);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new DaoException("Error while get all users from database", e);
        }
    }

    @Override
    public User findByEmail(String email) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(DatabaseQuery.FIND_USER_BY_EMAIL)) {
            statement.setString(1, email);
            User user = null;
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = createUserFromSql(resultSet);
            }
            return user;
        } catch (SQLException e) {
            throw new DaoException("Error while finding user by login from database", e);
        }
    }

    private User createUserFromSql(ResultSet resultSet) throws SQLException {
        String login = resultSet.getString(LOGIN_COLUMN_NAME);
        boolean isAdmin = resultSet.getBoolean(IS_ADMIN_COLUMN_NAME);
        boolean isBlocked = resultSet.getBoolean(IS_BLOCKED_COLUMN_NAME);
        String email = resultSet.getString(EMAIL_COLUMN_NAME);
        String name = resultSet.getString(NAME_COLUMN_NAME);
        String surname = resultSet.getString(SURNAME_COLUMN_NAME);
        Account account = new Account(login, isAdmin, isBlocked);

        return new User(account, email, name, surname);
    }
}
