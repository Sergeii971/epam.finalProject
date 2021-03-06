package com.verbovskiy.finalproject.model.dao.impl;

import com.verbovskiy.finalproject.exception.DaoException;
import com.verbovskiy.finalproject.model.connection.ConnectionPool;
import com.verbovskiy.finalproject.model.dao.AccountDao;
import com.verbovskiy.finalproject.model.dao.CarDao;
import com.verbovskiy.finalproject.model.dao.ColumnName;
import com.verbovskiy.finalproject.model.dao.UserDao;
import com.verbovskiy.finalproject.model.dao.query.DatabaseQuery;
import com.verbovskiy.finalproject.model.entity.*;
import com.verbovskiy.finalproject.util.date_converter.DateConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type Car dao.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public class CarDaoImpl implements CarDao {
    private static CarDao instance;

    private CarDaoImpl() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static CarDao getInstance() {
        if (instance == null) {
            instance = new CarDaoImpl();
        }
        return instance;
    }

    @Override
    public void add(String brand, String price, String description, String imageName,
                    boolean isAvailable, long addedDate, String model, String manufactureYear,
                    String color, String boxType, String engineType) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();

        try (Connection connection = connectionPool.getConnection();
                PreparedStatement carStatement = connection.prepareStatement(DatabaseQuery.ADD_CAR)) {
            carStatement.setString(1, brand);
            carStatement.setDouble(2, Double.parseDouble(price));
            carStatement.setString(3, description);
            carStatement.setString(4, imageName);
            carStatement.setBoolean(5, isAvailable);
            carStatement.setLong(6, addedDate);
            carStatement.setString(7, model);
            carStatement.setString(8, manufactureYear);
            carStatement.setString( 9, color);
            carStatement.setString(10, engineType);
            carStatement.setString(11, boxType);
            carStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error while adding car to database", e);
        }
    }

    @Override
    public List<Car> findAll() throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(DatabaseQuery.FIND_ALL_CARS)) {
            List<Car> cars = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Car car = createCarFromSql(resultSet);
                cars.add(car);
            }
            return cars;
        } catch (SQLException e) {
            throw new DaoException("Error while get all cars from database", e);
        }
    }

    @Override
    public void remove(long carId) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();

        try (Connection connection = connectionPool.getConnection();
                PreparedStatement accountStatement = connection.prepareStatement(DatabaseQuery.REMOVE_CAR)) {
            accountStatement.setLong(1, carId);
            accountStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error while removing car from database", e);
        }
    }

    @Override
    public void changeIsAvailableStatus(long carId, boolean status) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(DatabaseQuery.CHANGE_IS_AVAILABLE_CAR_STATUS)) {
            statement.setBoolean(1, status);
            statement.setLong(2, carId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error during changing car available status in database", e);
        }
    }

    @Override
    public List<Car> adminFindBySearchParameters(String searchParameter, double fromPrice, double toPrice, String brand,
                                                 String color, String boxType, String engineType) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(DatabaseQuery.ADMIN_FIND_CARS_BY_SEARCH_PARAMETERS)) {
            statement.setString(1, brand);
            statement.setDouble(2, fromPrice);
            statement.setDouble(3, toPrice);
            statement.setString( 4, color);
            statement.setString(5, engineType);
            statement.setString(6, boxType);
            statement.setString(7, searchParameter);
            List<Car> cars = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Car car = createCarFromSql(resultSet);
                cars.add(car);
            }
            return cars;
        } catch (SQLException e) {
            throw new DaoException("Error while finding cars by search parameters in database", e);
        }
    }

    @Override
    public List<Car> findAvailableCar() throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(DatabaseQuery.FIND_ALL_AVAILABLE_CAR)) {
            boolean isAvailable = true;
            statement.setBoolean(1, isAvailable);
            List<Car> cars = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Car car = createCarFromSql(resultSet);
                cars.add(car);
            }
            return cars;
        } catch (SQLException e) {
            throw new DaoException("Error while finding available car in database", e);
        }
    }

    @Override
    public List<Car> userFindBySearchParameters(String searchParameter, double fromPrice, double toPrice, String brand,
                                                String color, String boxType, String engineType, boolean isAvailable) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(DatabaseQuery.USER_FIND_CARS_BY_SEARCH_PARAMETERS)) {
            statement.setString(1, brand);
            statement.setDouble(2, fromPrice);
            statement.setDouble(3, toPrice);
            statement.setBoolean(4, isAvailable);
            statement.setString( 5, color);
            statement.setString(6, engineType);
            statement.setString(7, boxType);
            statement.setString(8, searchParameter);
            List<Car> cars = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Car car = createCarFromSql(resultSet);
                cars.add(car);
            }
            return cars;
        } catch (SQLException e) {
            throw new DaoException("Error while finding car by search parameters in database", e);
        }
    }

    @Override
    public Optional<String> findImageNameById(long carId) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(DatabaseQuery.FIND_IMAGE_NAME_BY_ID)) {
            statement.setLong(1, carId);
            ResultSet resultSet = statement.executeQuery();
            Optional<String> imageName = Optional.empty();
            if (resultSet.next()) {
                imageName = Optional.of(resultSet.getString(ColumnName.IMAGE_NAME));
            }
            return imageName;
        } catch (SQLException e) {
            throw new DaoException("Error while finding imageName by carId in database", e);
        }
    }

    @Override
    public Optional<Car> findById(long carId) throws DaoException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(DatabaseQuery.FIND_CAR_BY_ID)) {
            statement.setLong(1, carId);
            ResultSet resultSet = statement.executeQuery();
            Optional<Car> car = Optional.empty();
            if (resultSet.next()) {
                car = Optional.of(createCarFromSql(resultSet));
            }
            return car;
        } catch (SQLException e) {
            throw new DaoException("Error while finding car by carId in database", e);
        }
    }


    private Car createCarFromSql(ResultSet resultSet) throws SQLException {
        long carId = Long.parseLong(resultSet.getString(ColumnName.CAR_ID));
        CarBrand brand = CarBrand.valueOf(resultSet.getString(ColumnName.BRAND));
        double price = Double.parseDouble(resultSet.getString(ColumnName.PRICE));
        String description = resultSet.getString(ColumnName.DESCRIPTION);
        String imageName = resultSet.getString(ColumnName.IMAGE_NAME);
        boolean isAvailable = resultSet.getBoolean(ColumnName.IS_AVAILABLE);
        LocalDate addedDate = DateConverter.convertToDate(resultSet.getLong(ColumnName.ADDED_DATE));
        String model = resultSet.getString(ColumnName.MODEL);
        int manufactureYear = Integer.parseInt(resultSet.getString(ColumnName.MANUFACTURE_YEAR));
        CarColor color = CarColor.valueOf(resultSet.getString(ColumnName.COLOR));
        BoxType boxType = BoxType.valueOf(resultSet.getString(ColumnName.BOX_TYPE));
        CarEngine engineType = CarEngine.valueOf(resultSet.getString(ColumnName.ENGINE_TYPE));

        return new Car(carId, brand, model, manufactureYear, price, description, imageName, addedDate, isAvailable,
                color, boxType, engineType);
    }
}
