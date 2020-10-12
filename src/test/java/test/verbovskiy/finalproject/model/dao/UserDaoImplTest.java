package test.verbovskiy.finalproject.model.dao;

import com.verbovskiy.finalproject.exception.DaoException;
import com.verbovskiy.finalproject.model.dao.user.UserDao;
import com.verbovskiy.finalproject.model.dao.user.impl.UserDaoImpl;
import com.verbovskiy.finalproject.model.entity.Account;
import com.verbovskiy.finalproject.model.entity.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class UserDaoImplTest {
    UserDao dao;

    @BeforeClass
    public void setUp() throws DaoException {
        dao = new UserDaoImpl();
        dao.remove("sergeiverbovskiy4@gmail.com");
    }

    @Test
    public void findAllPositiveTest() throws DaoException {
        List<User> actual = dao.findAll();
        List<User> expected = new ArrayList<>();
        Account account = new Account("1234",false,false);
        User user = new User(account, "email", "name", "surname");
        expected.add(user);
        assertEquals(actual, expected);
    }

    @Test
    public void findByEmailPositiveTest() throws DaoException {
        User actual = dao.findByEmail("email");
        Account account = new Account("1234",false,false);
        User expected = new User(account, "email", "name", "surname");
        assertEquals(actual, expected);
    }
}