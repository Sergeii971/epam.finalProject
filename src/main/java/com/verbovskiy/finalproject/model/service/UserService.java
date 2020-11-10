package com.verbovskiy.finalproject.model.service;

import com.verbovskiy.finalproject.controller.AttributeKey;
import com.verbovskiy.finalproject.controller.command.RequestParameter;
import com.verbovskiy.finalproject.exception.DaoException;
import com.verbovskiy.finalproject.exception.EncryptionException;
import com.verbovskiy.finalproject.exception.ServiceException;
import com.verbovskiy.finalproject.model.comparator.user.UserComparator;
import com.verbovskiy.finalproject.model.dao.AccountDao;
import com.verbovskiy.finalproject.model.dao.UserDao;
import com.verbovskiy.finalproject.model.dao.impl.AccountDaoImpl;
import com.verbovskiy.finalproject.model.dao.impl.UserDaoImpl;
import com.verbovskiy.finalproject.model.entity.Account;
import com.verbovskiy.finalproject.model.entity.User;
import com.verbovskiy.finalproject.util.encryption.Cryptographer;
import com.verbovskiy.finalproject.util.validator.UserValidator;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserService {
    public Map<String, Boolean> add(String login, String password, boolean isAdmin, boolean isBlocked, boolean isConfirmed,
                                   String email, String name, String surname) throws ServiceException {
        AccountDao accountDao = new AccountDaoImpl();
        UserDao userDao = new UserDaoImpl();
        try {
            Map<String, Boolean> incorrectParameter = UserValidator.validateUserData(email, password, name, surname);
            if (!accountDao.findByLogin(login).isPresent()) {
                if (incorrectParameter.size() == 0) {
                    Cryptographer cryptographer = new Cryptographer();
                    String encryptedPassword = cryptographer.encrypt(password);
                    userDao.add(login, email, name, surname, encryptedPassword, isAdmin, isBlocked, isConfirmed);
                }
            } else {
                incorrectParameter.put(AttributeKey.LOGIN_EXIST, true);
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

    public List<User> findAllUser() throws ServiceException {
        UserDao dao = new UserDaoImpl();
        try {
            return dao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("error while find information about user", e);
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

    public Optional<User> findAdminByEmail(String email) throws ServiceException {
        UserDao dao = new UserDaoImpl();
        try {
            Optional<User> admin = dao.findByEmail(email);
            if (!admin.isPresent()) {
                throw new ServiceException("error while finding admin in database");
            }
            return admin;
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

    public boolean isConfirmed(String login) throws ServiceException {
        if ((login == null) || (login.isEmpty())) {
            throw new ServiceException("incorrect user data");
        }
        try {
            AccountDao dao = new AccountDaoImpl();
            Optional<Account> account = dao.findByLogin(login);
            if (!account.isPresent()) {
                throw new ServiceException("error while find information about user");
            }
            return account.get().isConfirmed();
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
                dao.changeConfirmationStatus(login.get(), true);
            }
            return result;
        } catch (DaoException | EncryptionException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public boolean updatePassword(String login, String password, String passwordConfirmation)
            throws ServiceException {
        boolean result = false;
        try {
            if (UserValidator.validatePasswords(password, passwordConfirmation)) {
                Cryptographer cryptographer = new Cryptographer();
                String encryptedPassword = cryptographer.encrypt(password);
                AccountDao dao = new AccountDaoImpl();
                dao.changePassword(login, encryptedPassword);
                result = true;
            }
            return result;
        } catch (EncryptionException | DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public void updateUserBlockStatus(String login, boolean isBlocked) throws ServiceException {
        try {
        AccountDao dao = new AccountDaoImpl();
        dao.changeBlockStatus(login, isBlocked);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<User> filterUsers(String userStatus) throws ServiceException {
        try {
            if (userStatus == null || userStatus.isEmpty()) {
                throw new ServiceException("incorrect user status");
            }
            List<User> users;
            UserDao dao = new UserDaoImpl();
            if (userStatus.equals(RequestParameter.IS_BLOCKED)) {
                users = dao.findBlockedStatusUsers();
            } else {
                if (userStatus.equals(RequestParameter.IS_CONFIRMED)) {
                    users = dao.findNotConfirmedStatusUsers();
                } else {
                    users = dao.findAll();
                }
            }
            return users;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<User> sortUsers(String sortType, List<User> users) throws ServiceException {
        if (sortType == null || sortType.isEmpty()) {
            throw new ServiceException("incorrect SortType");
        }
        try {
            Comparator comparator = UserComparator.valueOf(sortType.toUpperCase()).getComparator();
            if (comparator != null) {
                users.sort(comparator);
            } else {
                UserDao dao = new UserDaoImpl();
                 users = dao.findAll();
            }
            return users;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<User> findNotConfirmedUsers() throws ServiceException {
        try {
            UserDao dao = new UserDaoImpl();
            return dao.findNotConfirmedStatusUsers();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<User> searchUsers(String parameter) throws ServiceException {
        if (parameter == null) {
            throw new ServiceException("incorrect data");
        }
        try {
            UserDao dao = new UserDaoImpl();
            return dao.searchUsers(parameter);
        } catch (DaoException e) {
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
