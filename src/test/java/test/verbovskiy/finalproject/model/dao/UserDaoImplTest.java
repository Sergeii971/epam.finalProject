package test.verbovskiy.finalproject.model.dao;

import com.verbovskiy.finalproject.exception.DaoException;
import com.verbovskiy.finalproject.model.dao.UserDao;
import com.verbovskiy.finalproject.model.dao.impl.UserDaoImpl;
import com.verbovskiy.finalproject.model.entity.User;
import org.testng.annotations.AfterClass;
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
        User user1 = new User("1234","1234",false);
        User user2 = new User("qqqq","qqqq",true);
        dao.add(user1);
        dao.add(user2);
    }

    @Test
    public void testFindAll() throws DaoException {
        List<User> actual = dao.findAll();
        List<User> expected = new ArrayList<>();
        User user1 = new User("1234","1234",false);
        User user2 = new User("qqqq","qqqq",true);
        expected.add(user1);
        expected.add(user2);
        assertEquals(actual, expected);
    }

    @Test
    public void findByLoginPositiveTest() throws DaoException {
        User actual = dao.findByLogin("1234");
        User expected = new User("1234","1234",false);
        assertEquals(actual, expected);
    }

    @AfterClass
    public void tierDown() throws DaoException {
        User user1 = new User("1234","1234",false);
        User user2 = new User("qqqq","qqqq",true);
        dao.remove(user1);
        dao.remove(user2);
    }
}