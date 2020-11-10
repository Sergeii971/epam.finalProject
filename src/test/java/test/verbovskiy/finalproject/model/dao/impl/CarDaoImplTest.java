package test.verbovskiy.finalproject.model.dao.impl;

import com.verbovskiy.finalproject.exception.DaoException;
import com.verbovskiy.finalproject.model.dao.CarDao;
import com.verbovskiy.finalproject.model.dao.impl.CarDaoImpl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CarDaoImplTest {
    CarDao dao;

    @BeforeClass
    public void setUp() {
        dao = new CarDaoImpl();
    }

    @Test
    public void testAdd() {
    }

    @Test
    public void testFindAll() {
    }

    @Test
    public void testRemove() throws DaoException {
        dao.remove(45);
    }
}