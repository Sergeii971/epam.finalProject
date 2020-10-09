package com.verbovskiy.finalproject.model.dao.account;

import com.verbovskiy.finalproject.exception.DaoException;
import com.verbovskiy.finalproject.model.entity.Account;

import java.util.List;

public interface AccountDao {
    void add(String login, String password, boolean isAdmin, boolean isBlocked) throws DaoException;

    void remove(String login) throws DaoException;

    List<Account> findAll() throws DaoException;

    Account findByLogin(String login) throws DaoException;

    Account findByLoginPassword(String login, String password) throws DaoException;
}
