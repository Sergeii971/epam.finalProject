package com.verbovskiy.finalproject.model.service;

import com.verbovskiy.finalproject.exception.DaoException;
import com.verbovskiy.finalproject.exception.EncryptionException;
import com.verbovskiy.finalproject.exception.ServiceException;
import com.verbovskiy.finalproject.model.dao.AccountDao;
import com.verbovskiy.finalproject.model.dao.impl.AccountDaoImpl;
import com.verbovskiy.finalproject.model.dao.UserDao;
import com.verbovskiy.finalproject.model.dao.impl.UserDaoImpl;
import com.verbovskiy.finalproject.model.entity.Account;
import com.verbovskiy.finalproject.model.entity.User;
import com.verbovskiy.finalproject.model.validator.AccountValidator;
import com.verbovskiy.finalproject.util.encryption.Cryptographer;

import java.util.List;
import java.util.Optional;

public class UserService {
    public boolean add(String login, String password, boolean isAdmin, boolean isBlocked,
                    String email, String name, String surname) throws ServiceException {
        boolean result = true;
        AccountValidator validator = new AccountValidator();
        AccountDao accountDao = new AccountDaoImpl();
        UserDao userDao = new UserDaoImpl();
        try {
        if (!validator.validateLoginPassword(login, password) || (accountDao.findByLogin(login) != null)) {
            result = false;
        } else {
            Cryptographer cryptographer = new Cryptographer();
            String encryptedPassword = cryptographer.encrypt(password);
            userDao.add(login, email, name, surname, encryptedPassword, isAdmin, isBlocked);
        }
        return result;
        } catch (EncryptionException | DaoException e) {
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
            User user = userDao.findByEmail(email);
            if (user == null) {
                throw new ServiceException("incorrect user data");
            }
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
            String encryptedPassword = cryptographer.encrypt(password);
            Account account = dao.findByLoginPassword(login, encryptedPassword);
            if (account == null) {
                result = false;
            }
            return result;
        } catch (EncryptionException | DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Optional<Account> findByLogin(String login) throws ServiceException {
        AccountDao dao = new AccountDaoImpl();
        try {
              return Optional.of(dao.findByLogin(login));
        } catch (DaoException e) {
            throw new ServiceException("error while find information about user", e);
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

    public boolean ConfirmUser(String confirmationKey) throws ServiceException {
        if ((confirmationKey == null) || (confirmationKey.isEmpty())) {
            throw new ServiceException("incorrect user data");
        }
        boolean result = true;
        try {
            Optional<String> login = findUserLoginByConfirmationKey(confirmationKey);
            if (!login.isPresent()) {
                result = false;
            } else {
                AccountDao dao = new AccountDaoImpl();
                dao.changeUserBlockStatus(login.get(), true);
            }
            return result;
        } catch (DaoException | EncryptionException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    private Optional<String> findUserLoginByConfirmationKey(String confirmationKey) throws DaoException,
            ServiceException, EncryptionException {
        AccountDao dao = new AccountDaoImpl();
        List<Account> accounts = dao.findAll();

        if (accounts == null) {
            throw new ServiceException("error while confirm user");
        } else {
            Optional<String> login = Optional.empty();
            Cryptographer cryptographer = new Cryptographer();

            for (Account account : accounts) {
                String encryptedAccountData = cryptographer.encrypt(account.toString());
                if (encryptedAccountData.equals(confirmationKey)) {
                    login = Optional.of(account.getLogin());
                    break;
                }
            }
            return login;
        }
    }
}
