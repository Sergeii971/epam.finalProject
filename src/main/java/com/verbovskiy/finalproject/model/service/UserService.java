package com.verbovskiy.finalproject.model.service;

import com.verbovskiy.finalproject.controller.AttributeKey;
import com.verbovskiy.finalproject.controller.command.RequestParameter;
import com.verbovskiy.finalproject.exception.DaoException;
import com.verbovskiy.finalproject.exception.EncryptionException;
import com.verbovskiy.finalproject.exception.ServiceException;
import com.verbovskiy.finalproject.model.dao.AccountDao;
import com.verbovskiy.finalproject.model.dao.impl.AccountDaoImpl;
import com.verbovskiy.finalproject.model.dao.UserDao;
import com.verbovskiy.finalproject.model.dao.impl.UserDaoImpl;
import com.verbovskiy.finalproject.model.entity.Account;
import com.verbovskiy.finalproject.model.entity.User;
import com.verbovskiy.finalproject.util.encryption.Cryptographer;
import com.verbovskiy.finalproject.util.validator.UserValidator;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserService {
    public Map<String, Boolean> add(String login, String password, boolean isAdmin, boolean isBlocked,
                                   String email, String name, String surname) throws ServiceException {
        AccountDao accountDao = new AccountDaoImpl();
        UserDao userDao = new UserDaoImpl();
        try {
            Map<String, Boolean> incorrectParameter = UserValidator.validateUserData(email, password, name, surname);
            if (!accountDao.findByLogin(login).isPresent()) {
                if (incorrectParameter.size() == 0) {
                    Cryptographer cryptographer = new Cryptographer();
                    String encryptedPassword = cryptographer.encrypt(password);
                    userDao.add(login, email, name, surname, encryptedPassword, isAdmin, isBlocked);
                }
            } else {
                incorrectParameter.put(AttributeKey.LOGIN_EXIST, RequestParameter.IS_INCORRECT);
            }
            return incorrectParameter;
        } catch (EncryptionException | DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public void remove(String email) throws ServiceException {
        if ((email == null) || (email.isEmpty())) {
            throw new ServiceException("incorrect user data");
        }
        try {
            UserDao userDao = new UserDaoImpl();
            Optional<User> user = userDao.findByEmail(email);
            if (!user.isPresent()) {
                throw new ServiceException("incorrect user data");
            }
            userDao.remove(user.get().getEmail());
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
            Optional<Account> account = dao.findByLoginPassword(login, encryptedPassword);

            if (!account.isPresent()) {
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
              return dao.findByLogin(login);
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
            Optional<Account> account = dao.findByLogin(login);
            if (!account.isPresent()) {
                throw new ServiceException("error while find information about user");
            }
            return account.get().isAdmin();
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
            Optional<Account> account = dao.findByLogin(login);
            if (!account.isPresent()) {
                throw new ServiceException("error while find information about user");
            }
            return account.get().isBlocked();
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
                dao.changeUserBlockStatus(login.get(), false);
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
