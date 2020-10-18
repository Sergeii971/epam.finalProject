package test.verbovskiy.finalproject.model.service;

import com.verbovskiy.finalproject.exception.ServiceException;
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
//        service.add("1234","1234",false,false, "email", "name", "surname");
//        service.add("qqqq","qqqq",true, false,"email2", "name2", "surname2");
    }

    @Test
    public void testVerifyUser() throws ServiceException {
//        boolean actual = service.ConfirmUser("1234");
//        boolean expected = true;
//        assertEquals(actual, expected);
    }
    
    @AfterClass
    public void tierDown() throws ServiceException {
//        service.remove("email");
//       service.remove("email2");
     //   service.remove("sergeiverbovskiy4@gmail.com");
    }
}