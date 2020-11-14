package com.verbovskiy.finalproject.model.service;

import com.verbovskiy.finalproject.exception.DaoException;
import com.verbovskiy.finalproject.exception.ServiceException;
import com.verbovskiy.finalproject.model.entity.Account;
import com.verbovskiy.finalproject.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The interface User service.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public interface UserService {
    /**
     * Add user
     *
     * @param login the login
     * @param email the email
     * @param name the name
     * @param surname the surname
     * @param password the password
     * @param isAdmin is admin
     * @param isBlocked is blocked
     * @param isConfirmed is confirmed
     * @throws DaoException the dao exception
     */
    Map<String, Boolean> add(String login, String password, boolean isAdmin, boolean isBlocked, boolean isConfirmed,
                             String email, String name, String surname) throws ServiceException;

    /**
     * Remove user
     *
     * @param email the email
     * @throws DaoException the dao exception
     */
    void remove(String email) throws ServiceException;

    /**
     * Find all users.
     *
     * @return the list of found users
     * @throws DaoException the dao exception
     */
    List<User> findAllUser() throws ServiceException;

    /**
     * verify account.
     *
     * @return boolean
     * @throws DaoException the dao exception
     */
    boolean verifyAccount(String login, String password) throws ServiceException;

    /**
     * Find account by login.
     *
     * @return  Optional
     * @throws DaoException the dao exception
     */
    Optional<Account> findByLogin(String login) throws ServiceException;

    /**
     * Find admin by email.
     *
     * @param email the email
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<User> findAdminByEmail(String email) throws ServiceException;

    /**
     * is user admin.
     *
     * @param login the login
     * @return  boolean
     * @throws DaoException the dao exception
     */
    boolean isAdmin(String login) throws ServiceException;

    /**
     * is user blocked.
     *
     * @param login the login
     * @return  boolean
     * @throws DaoException the dao exception
     */
    boolean isBlocked(String login) throws ServiceException;

    /**
     * is user confirmed.
     *
     * @param login the login
     * @return  boolean
     * @throws DaoException the dao exception
     */
    boolean isConfirmed(String login) throws ServiceException;

    /**
     * confirm user.
     *
     * @param confirmationKey the confirmation key
     * @return  boolean
     * @throws DaoException the dao exception
     */
    boolean ConfirmUser(String confirmationKey) throws ServiceException;

    /**
     * Change user password.
     * @return boolean
     * @throws DaoException the dao exception
     */
    boolean updatePassword(String login, String password, String passwordConfirmation)
            throws ServiceException;

    /**
     * Change user block status.
     *
     * @throws DaoException the dao exception
     */
    void updateUserBlockStatus(String login, boolean isBlocked) throws ServiceException;

    /**
     * filter users.
     *
     * @param userStatus the user status
     * @return the list of found users
     * @throws DaoException the dao exception
     */
    List<User> filterUsers(String userStatus) throws ServiceException;

    /**
     * sort users.
     *
     * @param sortType the sort type
     * @param users the users
     * @return the list of found users
     * @throws DaoException the dao exception
     */
    List<User> sortUsers(String sortType, List<User> users) throws ServiceException;

    /**
     * Find all not confirmed users.
     *
     * @return the list of found users
     * @throws DaoException the dao exception
     */
    List<User> findNotConfirmedUsers() throws ServiceException;

    /**
     * Find all users by search parameter.
     *
     * @param parameter the search parameter
     * @return the list of found users
     * @throws DaoException the dao exception
     */
    List<User> searchUsers(String parameter) throws ServiceException;
}
