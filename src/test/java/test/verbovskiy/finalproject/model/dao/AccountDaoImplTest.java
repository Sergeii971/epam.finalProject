package test.verbovskiy.finalproject.model.dao;

import com.verbovskiy.finalproject.exception.DaoException;
import com.verbovskiy.finalproject.model.dao.account.AccountDao;
import com.verbovskiy.finalproject.model.dao.account.impl.AccountDaoImpl;
import com.verbovskiy.finalproject.model.entity.Account;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class AccountDaoImplTest {
    AccountDao dao;

    @BeforeClass
    public void setUp() throws DaoException {
        dao = new AccountDaoImpl();
        dao.add("1234","1234",false,false);
        dao.add( "qqqq","qqqq",true, false);
    }

    @Test
    public void testFindAll() throws DaoException {
        List<Account> actual = dao.findAll();
        List<Account> expected = new ArrayList<>();
        Account account1 = new Account("1234",false,false);
        Account account2 = new Account("qqqq",true, false);
        expected.add(account1);
        expected.add(account2);
        assertEquals(actual, expected);
    }

    @Test
    public void findByLoginPositiveTest() throws DaoException {
        Account actual = dao.findByLogin("1234");
        Account expected = new Account("1234",false,false);
        assertEquals(actual, expected);
    }

    @AfterClass
    public void tierDown() throws DaoException {
        dao.remove("1234");
        dao.remove("qqqq");
    }
}