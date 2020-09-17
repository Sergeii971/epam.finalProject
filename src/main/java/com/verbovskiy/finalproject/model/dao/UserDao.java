package com.verbovskiy.finalproject.model.dao;

import com.verbovskiy.finalproject.exception.DaoException;
import com.verbovskiy.finalproject.model.entity.User;

import java.util.List;

public interface UserDao {
    void add(User user) throws DaoException;

    void remove(User user) throws DaoException;

    List<User> findAll() throws DaoException;

    User findByLogin(String login) throws DaoException;

    User findByLoginPassword(String login, String password) throws DaoException;
}
