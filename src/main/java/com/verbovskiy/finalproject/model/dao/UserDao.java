package com.verbovskiy.finalproject.model.dao;

import com.verbovskiy.finalproject.exception.DaoException;
import com.verbovskiy.finalproject.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    void add(String login, String email, String name, String surname,String encryptedPassword,
             boolean isAdmin,boolean isBlocked) throws DaoException;

    void remove(String email) throws DaoException;

    List<User> findAll() throws DaoException;

    Optional<User> findByEmail(String email) throws DaoException;
}
