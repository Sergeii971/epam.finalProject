package com.verbovskiy.finalproject.model.dao;

import com.verbovskiy.finalproject.exception.DaoException;
import com.verbovskiy.finalproject.model.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderDao {

    void add(String userEmail, long carId, long date, boolean inProcessing) throws DaoException;

    void remove(long orderId) throws DaoException;

    List<Order> findBySearchParameters(String searchParameter, String brand, String color,
                                       String boxType, String engineType) throws DaoException;

    List<Order> findAll() throws DaoException;

    Optional<Order> findByCarId(long carId) throws DaoException;
}
