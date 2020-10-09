package test.verbovskiy.finalproject.model.service;

import com.verbovskiy.finalproject.exception.DaoException;
import com.verbovskiy.finalproject.exception.ServiceException;
import com.verbovskiy.finalproject.model.dao.account.AccountDao;
import com.verbovskiy.finalproject.model.dao.account.impl.AccountDaoImpl;
import com.verbovskiy.finalproject.model.entity.User;
import com.verbovskiy.finalproject.model.service.UserService;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class UserServiceTest {
    UserService service;

    @BeforeClass
    public void setUp() throws ServiceException {
        service = new UserService();
        //service.add("1234","1234",false,false, "email", "name", "surname");
       // service.add("qqqq","qqqq",true, false,"email2", "name2", "surname2");
    }

    @Test
    public void testVerifyUser() throws ServiceException {
        //User actual = service.verifyUser("1234","1234");
        //User expected = new User("1234",null,false);
        //assertEquals(actual, expected);
    }

    @AfterClass
    public void tierDown() throws ServiceException, DaoException {
        AccountDao dao = new AccountDaoImpl();
        //dao.remove("1234");
        //dao.remove("qqqq");
    }
}