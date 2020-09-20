package test.verbovskiy.finalproject.model.service;

import com.verbovskiy.finalproject.exception.ServiceException;
import com.verbovskiy.finalproject.model.service.UserService;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserServiceTest {
    UserService service;

    @BeforeClass
    public void setUp() throws ServiceException {
        service = new UserService();
        service.add("1234","1234",false,false);
        service.add("qqqq","qqqq",true, false);
    }

    @Test
    public void testVerifyUser() throws ServiceException {
        //User actual = service.verifyUser("1234","1234");
        //User expected = new User("1234",null,false);
        //assertEquals(actual, expected);
    }

    @AfterClass
    public void tierDown() throws ServiceException {
        //service.remove("1234","1234",false,false);
        //service.remove("qqqq", "qqqq",true,false);
    }
}