package com.verbovskiy.finalproject.model.service;

import com.verbovskiy.finalproject.exception.ServiceException;
import com.verbovskiy.finalproject.model.entity.Car;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CarService {
    Map<String, Boolean> add(String brand, String price, String description, String imageName, boolean isAvailable,
                             LocalDate addedDate, String model, String manufactureYear,
                             String color, String boxType, String engineType) throws ServiceException;

    void remove(long carId) throws ServiceException;

    Car findById(long carId) throws ServiceException;

    List<Car> findAllCars() throws ServiceException;

    Optional<List<Car>> findCarsByParameters(String searchParameter, String fromPrice, String toPrice, String brand,
                                             String color, String boxType, String engineType, boolean isAdmin) throws ServiceException;

    List<Car> findAvailableCar() throws ServiceException;

    void updateIsAvailableStatus(long carId, boolean status) throws ServiceException;
}
