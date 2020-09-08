package com.verbovskiy.finalproject.model.dao.impl;

import com.verbovskiy.finalproject.exception.DaoException;
import com.verbovskiy.finalproject.model.connection.ConnectionPool;
import com.verbovskiy.finalproject.model.dao.UserDao;
import com.verbovskiy.finalproject.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private  final String ADD_USER = "INSERT INTO user(login, password, isAdmin) " +
            "VALUES (?, ?, ?)";
    private  final String REMOVE_USER = "DELETE FROM user " + "WHERE login = ?";
    private final String FIND_ALL_USERS = "SELECT login, password, isAdmin " + "FROM user";
    private final String FIND_BOOK_BY_LOGIN = FIND_ALL_USERS + " WHERE login = ? ";
    private final String LOGIN_COLUMN_NAME = "login";
    private final String PASSWORD_COLUMN_NAME = "password";
    private final String IS_ADMIN_COLUMN_NAME = "isAdmin";

    @Override
    public void add(User user) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(ADD_USER)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setBoolean(3, user.isAdmin());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error while adding user to database", e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void remove(User user) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(REMOVE_USER)) {
            statement.setString(1, user.getLogin());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error while removing user to database", e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> users = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_USERS)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    User user = createUserFromSql(resultSet);
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error while get all users from database", e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return users;
    }

    @Override
    public User findByLogin(String login) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        User user = null;
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(FIND_BOOK_BY_LOGIN)) {
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = createUserFromSql(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding user by login from database", e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return user;
    }

    private User createUserFromSql(ResultSet resultSet) throws DaoException {
        String login;
        String password;
        boolean isAdmin;
        try {
            login = resultSet.getString(LOGIN_COLUMN_NAME);
            password = resultSet.getString(PASSWORD_COLUMN_NAME);
            isAdmin = resultSet.getBoolean(IS_ADMIN_COLUMN_NAME);
        } catch (SQLException e) {
            throw new DaoException("Error while finding user", e);
        }
        return new User(login, password, isAdmin);
    }
}
