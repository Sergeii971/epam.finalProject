package com.verbovskiy.finalproject.model.dao;

import com.verbovskiy.finalproject.exception.DaoException;
import com.verbovskiy.finalproject.model.entity.Account;

import java.util.List;
import java.util.Optional;

public interface AccountDao {
    List<Account> findAll() throws DaoException;

    void changeUserBlockStatus(String login, boolean status) throws DaoException;

    Optional<Account> findByLogin(String login) throws DaoException;

    Optional<Account> findByLoginPassword(String login, String password) throws DaoException;
}
