package com.verbovskiy.finalproject.model.dao;

import com.verbovskiy.finalproject.exception.DaoException;
import com.verbovskiy.finalproject.model.entity.UserOrder;

import java.util.List;
import java.util.Optional;

public interface OrderDao {

    void add(String userEmail, long carId, long date, boolean inProcessing) throws DaoException;

    void remove(long orderId) throws DaoException;

    List<UserOrder> findBySearchParameters(String searchParameter, String brand, String color,
                                           String boxType, String engineType) throws DaoException;

    void changeInProcessingStatus(long orderId, boolean status) throws DaoException;

    List<UserOrder> findAll() throws DaoException;

    List<UserOrder> findByUserEmail(String email) throws DaoException;

    Optional<UserOrder> findByCarId(long carId) throws DaoException;
}
