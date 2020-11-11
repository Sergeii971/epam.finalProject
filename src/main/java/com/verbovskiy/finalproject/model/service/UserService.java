package com.verbovskiy.finalproject.model.service;

import com.verbovskiy.finalproject.exception.ServiceException;
import com.verbovskiy.finalproject.model.entity.Account;
import com.verbovskiy.finalproject.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    Map<String, Boolean> add(String login, String password, boolean isAdmin, boolean isBlocked, boolean isConfirmed,
                             String email, String name, String surname) throws ServiceException;

    void remove(String email) throws ServiceException;

    List<User> findAllUser() throws ServiceException;

    boolean verifyAccount(String login, String password) throws ServiceException;

    Optional<Account> findByLogin(String login) throws ServiceException;

    Optional<User> findAdminByEmail(String email) throws ServiceException;

    boolean isAdmin(String login) throws ServiceException;

    boolean isBlocked(String login) throws ServiceException;

    boolean isConfirmed(String login) throws ServiceException;

    boolean ConfirmUser(String confirmationKey) throws ServiceException;

    boolean updatePassword(String login, String password, String passwordConfirmation)
            throws ServiceException;

    void updateUserBlockStatus(String login, boolean isBlocked) throws ServiceException;

    List<User> filterUsers(String userStatus) throws ServiceException;

    List<User> sortUsers(String sortType, List<User> users) throws ServiceException;

    List<User> findNotConfirmedUsers() throws ServiceException;

    List<User> searchUsers(String parameter) throws ServiceException;
}
