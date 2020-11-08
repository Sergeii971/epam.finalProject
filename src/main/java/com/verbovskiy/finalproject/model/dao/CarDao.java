package com.verbovskiy.finalproject.model.dao;

import com.verbovskiy.finalproject.exception.DaoException;
import com.verbovskiy.finalproject.model.entity.Car;
import com.verbovskiy.finalproject.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface CarDao {

    void add(String brand, String price, String description, String imageName,
             boolean isAvailable, long addedDate, String model, String manufactureYear,
             String color, String boxType, String engineType) throws DaoException;

    List<Car> findAll() throws DaoException;

    void remove(long carId) throws DaoException;

    void changeIsAvailableStatus(long carId, boolean status) throws DaoException;

    Optional<String> findImageNameById(long carId) throws DaoException;
}
