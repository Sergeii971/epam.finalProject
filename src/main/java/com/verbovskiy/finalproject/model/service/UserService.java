package com.verbovskiy.finalproject.model.service;

import com.verbovskiy.finalproject.exception.DaoException;
import com.verbovskiy.finalproject.exception.EncryptionException;
import com.verbovskiy.finalproject.exception.ServiceException;
import com.verbovskiy.finalproject.model.dao.account.AccountDao;
import com.verbovskiy.finalproject.model.dao.account.impl.AccountDaoImpl;
import com.verbovskiy.finalproject.model.dao.user.UserDao;
import com.verbovskiy.finalproject.model.dao.user.impl.UserDaoImpl;
import com.verbovskiy.finalproject.model.entity.Account;
import com.verbovskiy.finalproject.model.entity.User;
import com.verbovskiy.finalproject.model.validator.AccountValidator;
import com.verbovskiy.finalproject.util.encryption.Cryptographer;

import java.util.List;

public class UserService {
    public void add(String login, String password, boolean isAdmin, boolean isBlocked,
                    String email, String name, String surname) throws ServiceException {
        AccountValidator validator = new AccountValidator();
        AccountDao accountDao = new AccountDaoImpl();
        UserDao userDao = new UserDaoImpl();

        if (!validator.validateLoginPassword(login, password)) {
            throw new ServiceException("incorrect user data");
        }
        boolean flag = false;
        try {
            Cryptographer cryptographer = new Cryptographer();
            String encryptedPassword = cryptographer.encryptPassword(password);
            accountDao.add(login, encryptedPassword, isAdmin, isBlocked);
            flag = true;
            userDao.add(login, email, name, surname);

        } catch (EncryptionException | DaoException e) {
            if (flag) {
                try {
                    accountDao.remove(login);
                } catch (DaoException e1) {
                    throw new ServiceException(e1.getMessage());
                }
            }
            throw new ServiceException(e.getMessage());
        }
    }

    public void remove(String email) throws ServiceException {
        AccountValidator validator = new AccountValidator();

        if ((email == null) || (email.isEmpty())) {
            throw new ServiceException("incorrect user data");
        }
        try {
            UserDao userDao = new UserDaoImpl();
            AccountDao accountDao = new AccountDaoImpl();
            User user = userDao.findByEmail(email);
            if (user == null) {
                throw new ServiceException("incorrect user data");
            }
            Account account = user.getAccount();
            accountDao.remove(account.getLogin());
            userDao.remove(user.getEmail());
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public boolean verifyAccount(String login, String password) throws ServiceException {
        if ((login == null) || (login.isEmpty()) || (password == null) || (password.isEmpty())) {
            throw new ServiceException("incorrect user data");
        }
        try {
            boolean result = true;
            AccountDao dao = new AccountDaoImpl();
            Cryptographer cryptographer = new Cryptographer();
            String encryptedPassword = cryptographer.encryptPassword(password);
            Account account = dao.findByLoginPassword(login, encryptedPassword);
            if (account == null) {
                result = false;
            }
            return result;
        } catch (EncryptionException | DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public boolean isAdmin(String login) throws ServiceException {
        if ((login == null) || (login.isEmpty())) {
            throw new ServiceException("incorrect user data");
        }
        try {
            AccountDao dao = new AccountDaoImpl();
            Account account = dao.findByLogin(login);
            if (account == null) {
                throw new ServiceException("error while find information about user");
            }
            return account.isAdmin();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public boolean isBlocked(String login) throws ServiceException {
        if ((login == null) || (login.isEmpty())) {
            throw new ServiceException("incorrect user data");
        }
        try {
            AccountDao dao = new AccountDaoImpl();
            Account account = dao.findByLogin(login);
            if (account == null) {
                throw new ServiceException("error while find information about user");
            }
            return account.isBlocked();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public boolean ConfirmUser(String email) throws ServiceException {
        if ((email == null) || (email.isEmpty())) {
            throw new ServiceException("incorrect user data");
        }
        UserDao userDao = new UserDaoImpl();
        boolean result = true;
        try {
            List<User> users = userDao.findAll();
            if (users == null) {
                throw new ServiceException("error while add user data to database");
            } else {

            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
