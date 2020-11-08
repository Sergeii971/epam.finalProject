package test.verbovskiy.finalproject.model.dao.impl;

import com.verbovskiy.finalproject.exception.DaoException;
import com.verbovskiy.finalproject.model.dao.OrderDao;
import com.verbovskiy.finalproject.model.dao.impl.OrderDaoImpl;
import com.verbovskiy.finalproject.model.entity.Order;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

import static org.testng.Assert.*;

public class OrderDaoImplTest {
    @Test
    public void add() throws DaoException {
        OrderDao dao = new OrderDaoImpl();
    }


    @Test
    public void testFindByCarId() throws DaoException {
        OrderDao dao = new OrderDaoImpl();
        List<Order> order = dao.findAll();
        System.out.println(order.toString());
    }
}