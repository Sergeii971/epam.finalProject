package test.verbovskiy.finalproject.model.service.impl;

import com.verbovskiy.finalproject.exception.DaoException;
import com.verbovskiy.finalproject.exception.ServiceException;
import com.verbovskiy.finalproject.model.dao.impl.AccountDaoImpl;
import com.verbovskiy.finalproject.model.dao.impl.OrderDaoImpl;
import com.verbovskiy.finalproject.model.dao.impl.UserDaoImpl;
import com.verbovskiy.finalproject.model.entity.Account;
import com.verbovskiy.finalproject.model.entity.User;
import com.verbovskiy.finalproject.model.service.UserService;
import com.verbovskiy.finalproject.model.service.impl.UserServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockObjectFactory;
import org.testng.IObjectFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.ObjectFactory;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.testng.Assert.*;

@PowerMockIgnore({"com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*", "org.w3c.*",
        "com.sun.org.apache.xalan.*", "javax.management.*"})
@PrepareForTest({UserDaoImpl.class, AccountDaoImpl.class})
public class UserServiceTestImpl {
    UserDaoImpl userDao;
    AccountDaoImpl accountDao;

    @ObjectFactory
    public IObjectFactory setObjectFactory() {
        return new PowerMockObjectFactory();
    }

    @BeforeMethod
    public void setUp() {
        PowerMockito.mockStatic(UserDaoImpl.class);
        userDao = Mockito.mock(UserDaoImpl.class);
        Mockito.when(UserDaoImpl.getInstance()).thenReturn(userDao);
        PowerMockito.mockStatic(AccountDaoImpl.class);
        accountDao = Mockito.mock(AccountDaoImpl.class);
        Mockito.when(AccountDaoImpl.getInstance()).thenReturn(accountDao);
    }

    @Test
    public void verifyAccountPositiveTest() throws DaoException, ServiceException {
        String login = "epam.online.store@gmail.com";
        String password = "571D3BEB574F2D1905196D55E8570D8C03B4CCC20BB65BC641328B06FD8840E7BD8E83354B5E1D1D0A78AE" +
                "AD9CECE7F1B9B716E5687C357F7A46C694CB5B4668";
        boolean isAdmin = true;
        boolean isBlocked = false;
        boolean isConfirmed = true;
        Optional<Account> expected = Optional.of(new Account(login, isAdmin, isBlocked, isConfirmed));
        Mockito.when(accountDao.findByLoginPassword(Mockito.anyString(), Mockito.anyString())).thenReturn(expected);
        UserService userService = new UserServiceImpl();
        boolean actual =  userService.verifyAccount(login, "S12345678s&");
        assertTrue(actual);
    }

    @Test
    public void verifyAccountNegativeTest() throws DaoException, ServiceException {
        String login = "epam.online.store@gmail.com";
        String password = "571D3BEB574F2D1905196D55E8570D8C03B4CCC20BB65BC641328B06FD8840E7BD8E83354B5E1D1D0A78AE" +
                "AD9CECE7F1B9B716E5687C357F7A46C694CB5B4668";
        boolean isAdmin = true;
        boolean isBlocked = false;
        boolean isConfirmed = true;
        Optional<Account> expected = Optional.of(new Account(login, isAdmin, isBlocked, isConfirmed));
        Mockito.when(accountDao.findByLoginPassword(Mockito.anyString(), Mockito.anyString())).thenReturn(expected);
        UserService userService = new UserServiceImpl();
        boolean actual =  userService.verifyAccount(login, password);
        assertFalse(actual);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void verifyAccountExceptionTest() throws DaoException, ServiceException {
        String login = "epam.online.store@gmail.com";
        boolean isAdmin = true;
        boolean isBlocked = false;
        boolean isConfirmed = true;
        Optional<Account> account = Optional.of(new Account(login, isAdmin, isBlocked, isConfirmed));
        Mockito.when(accountDao.findByLogin(Mockito.anyString())).thenReturn(account);
        UserService userService = new UserServiceImpl();
        userService.verifyAccount(null, null);
    }

    @Test
    public void findByLoginPositiveTest() throws DaoException, ServiceException {
        String login = "epam.online.store@gmail.com";
        boolean isAdmin = true;
        boolean isBlocked = false;
        boolean isConfirmed = true;
        Optional<Account> expected = Optional.of(new Account(login, isAdmin, isBlocked, isConfirmed));
        Mockito.when(accountDao.findByLogin(Mockito.anyString())).thenReturn(expected);
        UserService userService = new UserServiceImpl();
        Optional<Account> actual =  userService.findByLogin(login);
        assertEquals(actual, expected);
    }

    @Test
    public void findByLoginNegativeTest() throws DaoException, ServiceException {
        String login = "epam.online.store@gmail.com";
        boolean isAdmin = true;
        boolean isBlocked = false;
        boolean isConfirmed = true;
        Optional<Account> account = Optional.of(new Account(login, isAdmin, isBlocked, isConfirmed));
        Optional<Account> expected = Optional.of(new Account(login, isAdmin, isBlocked, false));
        Mockito.when(accountDao.findByLogin(Mockito.anyString())).thenReturn(account);
        UserService userService = new UserServiceImpl();
        Optional<Account> actual =  userService.findByLogin("");
        assertNotEquals(actual, expected);
    }

    @Test
    public void findAdminByEmailPositiveTest() throws DaoException, ServiceException {
        String login = "epam.online.store@gmail.com";
        boolean isAdmin = true;
        boolean isBlocked = false;
        boolean isConfirmed = true;
        Account account = new Account(login, isAdmin, isBlocked, isConfirmed);
        String name = "Sergei";
        String surname = "Verbovskiy";
        Optional<User> user = Optional.of(new User(account, login, name, surname));
        Optional<User> expected = Optional.of(new User(account, login, name, surname));
        Mockito.when(userDao.findByEmail(Mockito.anyString())).thenReturn(user);
        UserService userService = new UserServiceImpl();
        Optional<User> actual =  userService.findAdminByEmail(login);
        assertEquals(actual, expected);
    }

    @Test
    public void findAdminByEmailNegativeTest() throws DaoException, ServiceException {
        String login = "epam.online.store@gmail.com";
        boolean isAdmin = true;
        boolean isBlocked = false;
        boolean isConfirmed = true;
        Account account = new Account(login, isAdmin, isBlocked, isConfirmed);
        String name = "Sergei";
        String surname = "Verbovskiy";
        Optional<User> user = Optional.of(new User(account, login, name, surname));
        Optional<User> expected = Optional.of(new User(account, login, name, ""));
        Mockito.when(userDao.findByEmail(Mockito.anyString())).thenReturn(user);
        UserService userService = new UserServiceImpl();
        Optional<User> actual =  userService.findAdminByEmail(login);
        assertNotEquals(actual, expected);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void findAdminByEmailExceptionTest() throws DaoException, ServiceException {
        String login = "epam.online.store@gmail.com";
        boolean isAdmin = true;
        boolean isBlocked = false;
        boolean isConfirmed = true;
        Account account = new Account(login, isAdmin, isBlocked, isConfirmed);
        String name = "Sergei";
        String surname = "Verbovskiy";
        Optional<User> user = Optional.of(new User(account, login, name, surname));
        Mockito.when(userDao.findByEmail(Mockito.anyString())).thenReturn(user);
        UserService userService = new UserServiceImpl();
        Optional<User> actual =  userService.findAdminByEmail(null);
    }

    @Test
    public void isAdminPositiveTest() throws DaoException, ServiceException {
        String login = "epam.online.store@gmail.com";
        boolean isAdmin = true;
        boolean isBlocked = false;
        boolean isConfirmed = true;
        Optional<Account> account = Optional.of(new Account(login, isAdmin, isBlocked, isConfirmed));
        Mockito.when(accountDao.findByLogin(Mockito.anyString())).thenReturn(account);
        UserService userService = new UserServiceImpl();
        boolean actual =  userService.isAdmin(login);
        assertTrue(actual);
    }

    @Test
    public void isAdminNegativeTest() throws DaoException, ServiceException {
        String login = "sergeiverbovskiy4@gmail.com";
        boolean isAdmin = false;
        boolean isBlocked = false;
        boolean isConfirmed = true;
        Optional<Account> account = Optional.of(new Account(login, isAdmin, isBlocked, isConfirmed));
        Mockito.when(accountDao.findByLogin(login)).thenReturn(account);
        UserService userService = new UserServiceImpl();
        boolean actual =  userService.isAdmin("sergeiverbovskiy4@gmail.com");
        assertFalse(actual);
    }
}