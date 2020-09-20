package com.verbovskiy.finalproject.model.service;

import com.verbovskiy.finalproject.exception.DaoException;
import com.verbovskiy.finalproject.exception.EncryptionException;
import com.verbovskiy.finalproject.exception.ServiceException;
import com.verbovskiy.finalproject.model.dao.AccountDao;
import com.verbovskiy.finalproject.model.dao.impl.AccountDaoImpl;
import com.verbovskiy.finalproject.model.entity.Account;
import com.verbovskiy.finalproject.model.validator.AccountValidator;
import com.verbovskiy.finalproject.util.encryption.PasswordEncryption;

public class UserService {
    public void add(String login, String password, boolean isAdmin, boolean isBlocked) throws ServiceException {
        AccountValidator validator = new AccountValidator();
        AccountDao dao = new AccountDaoImpl();

        if (!validator.validateLoginPassword(login, password)) {
            throw new ServiceException("incorrect user data");
        }
        try {
            PasswordEncryption cryptographer = new PasswordEncryption();
            String encryptedPassword = cryptographer.encryptPassword(password);
            dao.add(login, encryptedPassword, isAdmin, isBlocked);
        } catch (EncryptionException | DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public void remove(String login) throws ServiceException {
        AccountValidator validator = new AccountValidator();

        if ((login == null) || (login.isEmpty())) {
            throw new ServiceException("incorrect user data");
        }
        try {
            AccountDao dao = new AccountDaoImpl();
            Account account1 = dao.findByLogin(login);
            if (account1 == null) {
                throw new ServiceException("incorrect user data");
            }
            dao.remove(login);
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
            PasswordEncryption cryptographer = new PasswordEncryption();
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
}
