package com.verbovskiy.finalproject.model.dao.user;

import com.verbovskiy.finalproject.exception.DaoException;
import com.verbovskiy.finalproject.model.entity.Account;
import com.verbovskiy.finalproject.model.entity.User;

import java.util.List;

public interface UserDao {
    void add(String login, String email, String name, String surname) throws DaoException;

    void remove(String email) throws DaoException;

    List<User> findAll() throws DaoException;

    User findByEmail(String email) throws DaoException;
}
