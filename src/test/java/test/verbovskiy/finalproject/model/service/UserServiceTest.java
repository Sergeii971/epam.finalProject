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
      // service.add("epam.online.store@gmail.com","S12345678s&",true,false, true, "epam.online.store@gmail.com", "Sergei", "Verbovskiy");
//        service.add("qqqq","qqqq",true, false,"email2", "name2", "surname2");
    }

    @Test
    public void testVerifyAccount() throws ServiceException {
       // boolean actual = service.verifyAccount("sergeiverbovskiy4@gmail.com", "12345678");
        // boolean expected = true;
        //assertEquals(actual, expected);
    }
    
    @AfterClass
    public void tierDown() throws ServiceException {
        //service.remove("1234");
//       service.remove("email2");
       service.remove("sergeiverbovskiy4@gmail.com");
       //service.remove("epam.online.store@gmail.com");
    }
}