package com.verbovskiy.finalproject.model.service.impl;

import com.verbovskiy.finalproject.exception.DaoException;
import com.verbovskiy.finalproject.exception.ServiceException;
import com.verbovskiy.finalproject.model.dao.AccountDao;
import com.verbovskiy.finalproject.model.dao.CarDao;
import com.verbovskiy.finalproject.model.dao.UserDao;
import com.verbovskiy.finalproject.model.dao.impl.AccountDaoImpl;
import com.verbovskiy.finalproject.model.dao.impl.CarDaoImpl;
import com.verbovskiy.finalproject.model.dao.impl.UserDaoImpl;
import com.verbovskiy.finalproject.model.entity.Account;
import com.verbovskiy.finalproject.model.entity.Car;
import com.verbovskiy.finalproject.model.entity.User;
import com.verbovskiy.finalproject.model.service.CarService;
import com.verbovskiy.finalproject.util.date_converter.DateConverter;
import com.verbovskiy.finalproject.util.validator.CarValidator;
import com.verbovskiy.finalproject.util.validator.SearchValidator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CarServiceImpl implements CarService {
    private static final String DEFAULT_IMAGE_NAME = "unknown.png";
    private static final String FOLDER_PATH = "C:\\Users\\sergei\\IdeaProjects\\epam.finalProject\\" +
            "target\\epam_finalProject-1.0-SNAPSHOT\\uploads\\";

    @Override
    public Map<String, Boolean> add(String brand, String price, String description, String imageName, boolean isAvailable,
                                    LocalDate addedDate, String model, String manufactureYear,
                                    String color, String boxType, String engineType) throws ServiceException {
        try {
            Map<String, Boolean> incorrectParameter = CarValidator.validateCarData(price, description, model, manufactureYear);
            if (incorrectParameter.size() == 0) {
                CarDao dao = new CarDaoImpl();
                long dateLongFormat = DateConverter.convertToLong(addedDate);
                if ((imageName == null) || (imageName.isEmpty())) {
                    imageName = DEFAULT_IMAGE_NAME;
                }
                dao.add(brand, price, description, imageName, isAvailable, dateLongFormat, model,
                        manufactureYear, color, boxType, engineType);
            }
            return incorrectParameter;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void remove(long carId) throws ServiceException {
        try {
            CarDao dao = new CarDaoImpl();
            Optional<String> imageName = dao.findImageNameById(carId);
            if (!imageName.isPresent()) {
                throw new ServiceException("error while find imageName");
            }
            String filePath = new StringBuilder().append(FOLDER_PATH).append(imageName.get()).toString();
            if (Files.exists(Paths.get(filePath)) && !imageName.get().equals(DEFAULT_IMAGE_NAME)) {
                Files.delete(Paths.get(filePath));
            }
            dao.remove(carId);
        } catch (DaoException | IOException e) {
            throw new ServiceException("error while remove car", e);
        }

    }

    @Override
    public Car findById(long carId) throws ServiceException {
        CarDao dao = new CarDaoImpl();
        try {
            Optional<Car> car = dao.findById(carId);
            if (!car.isPresent()) {
                throw new ServiceException("error while find information about car by id");
            }
            return car.get();
        } catch (DaoException e) {
            throw new ServiceException("error while find information about car by id", e);
        }
    }

    @Override
    public List<Car> findAllCars() throws ServiceException {
        CarDao dao = new CarDaoImpl();
        try {
            return dao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("error while find information about cars", e);
        }
    }

    @Override
    public Optional<List<Car>> findCarsByParameters(String searchParameter, String fromPrice, String toPrice, String brand,
                                                    String color, String boxType, String engineType, boolean isAdmin) throws ServiceException {
        Optional<List<Car>> cars = Optional.empty();

        if (SearchValidator.validateSearch(searchParameter) && SearchValidator.validatePrice(fromPrice)
                && SearchValidator.validatePrice(toPrice)) {
            try {
                double fromPriceDoubleFormat = fromPrice.isEmpty() ? 0 : Double.parseDouble(fromPrice);
                double toPriceDoubleFormat = toPrice.isEmpty() ? Double.MAX_VALUE : Double.parseDouble(toPrice);
                CarDao dao = new CarDaoImpl();
                if (isAdmin) {
                    cars = Optional.of(dao.adminFindBySearchParameters(searchParameter, fromPriceDoubleFormat,
                            toPriceDoubleFormat, brand, color, boxType, engineType));
                } else {
                    boolean isAvailable = true;
                    cars = Optional.of(dao.userFindBySearchParameters(searchParameter, fromPriceDoubleFormat,
                            toPriceDoubleFormat, brand, color, boxType, engineType, isAvailable));
                }
            } catch (DaoException e) {
                throw new ServiceException(e.getMessage());
            }
        }
        return cars;
    }

    @Override
    public List<Car> findAvailableCar() throws ServiceException {
        try {
            CarDao dao = new CarDaoImpl();
            return dao.findAvailableCar();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void updateIsAvailableStatus(long carId, boolean status) throws ServiceException {
        try {
            CarDao dao = new CarDaoImpl();
            dao.changeIsAvailableStatus(carId, status);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
