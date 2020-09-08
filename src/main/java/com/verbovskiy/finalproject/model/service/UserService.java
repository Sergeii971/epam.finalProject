package com.verbovskiy.finalproject.model.service;

import com.verbovskiy.finalproject.exception.DaoException;
import com.verbovskiy.finalproject.exception.EncryptionException;
import com.verbovskiy.finalproject.exception.ServiceException;
import com.verbovskiy.finalproject.model.dao.UserDao;
import com.verbovskiy.finalproject.model.dao.impl.UserDaoImpl;
import com.verbovskiy.finalproject.model.entity.User;
import com.verbovskiy.finalproject.model.reader.KeyReader;
import com.verbovskiy.finalproject.model.validator.UserValidator;
import com.verbovskiy.finalproject.model.writer.KeyWriter;
import com.verbovskiy.finalproject.util.PasswordEncryption;

import javax.crypto.Cipher;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class UserService {
    public static final String NAME_OF_FILE_WITH_ENCRYPT_KEY = "data/key.txt"; //todo fileName in service,use files in project
    public final String ENCODING_TYPE = "ISO8859_1";

    public void add(User user) throws ServiceException {
        UserValidator validator = new UserValidator();
        UserDao dao = new UserDaoImpl();

        if (!validator.validateUserData(user)) {
            throw new ServiceException("incorrect user data");
        }
        try {
            List<User> usersWithPasswordEncryptedWithOldKey = dao.findAll();
            PasswordEncryption newCryptographer = new PasswordEncryption();

            if (usersWithPasswordEncryptedWithOldKey.size() != 0) {
                if (validator.isLoginExists(user, usersWithPasswordEncryptedWithOldKey)) {
                    throw new ServiceException("login already exists");
                }
                List<User> usersWithDecryptedPassword = decryptPasswords(usersWithPasswordEncryptedWithOldKey);
                encryptUsersPasswordWithNewKey(newCryptographer, usersWithDecryptedPassword);
            }
            String password = user.getPassword();
            String encryptedPassword = encryptPassword(newCryptographer, password);
            user.setPassword(encryptedPassword);
            dao.add(user);
            KeyWriter writer = new KeyWriter();
            writer.writeAdminSecretKey(newCryptographer);
        } catch (EncryptionException | DaoException | IOException e) {
            throw new ServiceException("error while adding user to database");
        }
    }

    public void remove(User user) throws ServiceException, DaoException {
        UserValidator validator = new UserValidator();
        UserDao dao = new UserDaoImpl();
        User user1 = dao.findByLogin(user.getLogin());

        if (!validator.validateUserData(user) || (user1 == null)) {
            throw new ServiceException("incorrect user data");
        }
        dao.remove(user);
    }

    public User verifyUser(String login) throws ServiceException, DaoException, EncryptionException, IOException {
        if (login.isEmpty()) {
            throw new ServiceException("incorrect user data");
        }
        UserDao dao = new UserDaoImpl();
        KeyReader reader = new KeyReader();
        PasswordEncryption cryptographer = reader.readEncryptKey();
        User user = dao.findByLogin(login);
        if (user == null) {
            throw new ServiceException("incorrect login");
        }
        String encryptedPassword = user.getPassword();
        String decryptedPassword = decryptPassword(encryptedPassword,cryptographer);

        user.setPassword(decryptedPassword);
        return user;
    }

    private String encryptPassword(PasswordEncryption cryptographer, String password)
            throws EncryptionException, UnsupportedEncodingException {
        byte[] passwordByteFormat = password.getBytes(ENCODING_TYPE);
        byte[] encryptedPassword = cryptographer.makeEncryption(passwordByteFormat, Cipher.ENCRYPT_MODE);

        return new String(encryptedPassword);
    }

    private void encryptUsersPasswordWithNewKey(PasswordEncryption newCryptographer, List<User> users)
            throws DaoException, EncryptionException {
        UserDao dao = new UserDaoImpl();

        try {
        for (User user : users) {
            String password = user.getPassword();
            String PasswordEncryptedWithNewKey;

                PasswordEncryptedWithNewKey = encryptPassword(newCryptographer, password);
            user.setPassword(PasswordEncryptedWithNewKey);
            dao.remove(user);
            dao.add(user);
        }
        } catch (UnsupportedEncodingException e) {
            throw new EncryptionException(e);
        }
    }

    private List<User> decryptPasswords(List<User> users) throws EncryptionException, IOException {
        KeyReader reader = new KeyReader();
        PasswordEncryption cryptographer = reader.readEncryptKey();

        for (User user : users) {
            String encryptedPassword = user.getPassword();
            String password = decryptPassword(encryptedPassword, cryptographer);
            user.setPassword(password);
        }
        return users;
    }

    private String decryptPassword(String encryptedPassword, PasswordEncryption cryptographer)
            throws EncryptionException, UnsupportedEncodingException {
        byte[] encryptedPasswordByteFormat = encryptedPassword.getBytes(ENCODING_TYPE);
        byte[] decryptedPasswordByteFormat = cryptographer.makeEncryption(encryptedPasswordByteFormat,
                Cipher.DECRYPT_MODE);
        return new String(decryptedPasswordByteFormat);
    }
}
