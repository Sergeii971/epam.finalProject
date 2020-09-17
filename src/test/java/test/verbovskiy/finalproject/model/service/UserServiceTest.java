package test.verbovskiy.finalproject.model.service;

import com.verbovskiy.finalproject.exception.ServiceException;
import com.verbovskiy.finalproject.model.entity.User;
import com.verbovskiy.finalproject.model.service.UserService;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class UserServiceTest {
    UserService service;

    @BeforeClass
    public void setUp() throws ServiceException {
        service = new UserService();
        User user1 = new User("1234","1234",false);
        User user2 = new User("qqqq","qqqq",true);
        service.add(user1);
        //service.add(user2);
    }

    @Test
    public void testVerifyUser() throws ServiceException {
        //User actual = service.verifyUser("1234","1234");
        //User expected = new User("1234",null,false);
        //assertEquals(actual, expected);
    }

    @AfterClass
    public void tierDown() throws ServiceException {
        User user1 = new User("1234","1234",false);
        User user2 = new User("qqqq","qqqq",true);
        //service.remove(user1);
        //service.remove(user2);
    }
}