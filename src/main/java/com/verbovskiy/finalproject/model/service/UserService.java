package com.verbovskiy.finalproject.model.service;

import com.verbovskiy.finalproject.exception.DaoException;
import com.verbovskiy.finalproject.exception.EncryptionException;
import com.verbovskiy.finalproject.exception.ServiceException;
import com.verbovskiy.finalproject.model.dao.UserDao;
import com.verbovskiy.finalproject.model.dao.impl.UserDaoImpl;
import com.verbovskiy.finalproject.model.entity.User;
import com.verbovskiy.finalproject.model.validator.UserValidator;
import com.verbovskiy.finalproject.util.PasswordEncryption;

public class UserService {
    public void add(User user) throws ServiceException {
        UserValidator validator = new UserValidator();
        UserDao dao = new UserDaoImpl();

        if (!validator.validateUserData(user)) {
            throw new ServiceException("incorrect user data");
        }
        try {
            PasswordEncryption cryptographer = new PasswordEncryption();
            String password = user.getPassword();
            String encryptedPassword = cryptographer.encryptPassword(password);
            user.setPassword(encryptedPassword);
            dao.add(user);
        } catch (EncryptionException | DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public void remove(User user) throws ServiceException {
        UserValidator validator = new UserValidator();

        if (!validator.validateUserData(user)) {
            throw new ServiceException("incorrect user data");
        }
        try {
            UserDao dao = new UserDaoImpl();
            User user1 = dao.findByLogin(user.getLogin());
            if (user1 == null) {
                throw new ServiceException("incorrect user data");
            }
            dao.remove(user);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public boolean verifyUser(String login, String password) throws ServiceException {
        if ((login == null) || (login.isEmpty()) || (password == null) || (password.isEmpty())) {
            throw new ServiceException("incorrect user data");
        }
        try {
            boolean result = true;
            UserDao dao = new UserDaoImpl();
            PasswordEncryption cryptographer = new PasswordEncryption();
            String encryptedPassword = cryptographer.encryptPassword(password);
            User user = dao.findByLoginPassword(login, encryptedPassword);
            if (user == null) {
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
            UserDao dao = new UserDaoImpl();
            User user = dao.findByLogin(login);
            if (user == null) {
                throw new ServiceException("error while find information about user");
            }
            return user.isAdmin();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
