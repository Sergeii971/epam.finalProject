package com.verbovskiy.finalproject.model.service;

import com.verbovskiy.finalproject.exception.DaoException;
import com.verbovskiy.finalproject.exception.ServiceException;
import com.verbovskiy.finalproject.model.dao.CarDao;
import com.verbovskiy.finalproject.model.dao.OrderDao;
import com.verbovskiy.finalproject.model.dao.impl.CarDaoImpl;
import com.verbovskiy.finalproject.model.dao.impl.OrderDaoImpl;
import com.verbovskiy.finalproject.model.entity.Car;
import com.verbovskiy.finalproject.model.entity.Order;
import com.verbovskiy.finalproject.util.date_converter.DateConverter;
import com.verbovskiy.finalproject.util.validator.SearchValidator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class OrderService {
    public void add(String userEmail, long carId, LocalDate date, boolean inProcessing) throws ServiceException {
        try {
            OrderDao dao = new OrderDaoImpl();
            long dateLongFormat = DateConverter.convertToLong(date);
            dao.add(userEmail, carId, dateLongFormat, inProcessing);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public void remove(long orderId) throws ServiceException {
        try {
            OrderDao dao = new OrderDaoImpl();
            dao.remove(orderId);
        } catch (DaoException e) {
            throw new ServiceException("error while remove car", e);
        }

    }

    public Optional<Order> findByCarId(long carId) throws ServiceException {
        OrderDao dao = new OrderDaoImpl();
        try {
            return dao.findByCarId(carId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public Optional<List<Order>> findOrdersByParameters(String searchParameter, String brand, String color,
                                                        String boxType, String engineType) throws ServiceException {
        Optional<List<Order>> orders = Optional.empty();

        if (SearchValidator.validateSearch(searchParameter)) {
            try {
               OrderDao dao = new OrderDaoImpl();
               return Optional.of(dao.findBySearchParameters(searchParameter, brand, color, boxType, engineType));
            } catch (DaoException e) {
                throw new ServiceException(e.getMessage());
            }
        }
        return orders;
    }

    public List<Order> findAllOrders() throws ServiceException {
        OrderDao dao = new OrderDaoImpl();
        try {
            return dao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("error while find information about orders", e);
        }
    }
}
