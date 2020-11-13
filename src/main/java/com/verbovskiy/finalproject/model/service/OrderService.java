package com.verbovskiy.finalproject.model.service;

import com.verbovskiy.finalproject.exception.ServiceException;
import com.verbovskiy.finalproject.model.entity.UserOrder;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    void add(String userEmail, long carId, LocalDate date, boolean inProcessing) throws ServiceException;

    void remove(long orderId) throws ServiceException;

    Optional<UserOrder> findByCarId(long carId) throws ServiceException;

    Optional<List<UserOrder>> findOrdersByParameters(String searchParameter, String brand, String color,
                                                     String boxType, String engineType) throws ServiceException;

    Optional<List<UserOrder>> findOrdersByUserEmail(String email) throws ServiceException;

    void updateInProcessingStatus(long orderId, boolean status) throws ServiceException;

    List<UserOrder> findAllOrders() throws ServiceException;
}
