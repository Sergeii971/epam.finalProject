package com.verbovskiy.finalproject.model.connection;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {
    private static final String PROPERTIES_FILENAME = "C:\\Users\\sergei\\IdeaProjects\\epam.finalProject\\config\\database.properties";
    private static final String DRIVER_NAME = "db.driver";
    private static final String LOGIN = "db.login";
    private static final String PASSWORD = "db.password";
    private static final String URL = "db.url";
    private static final int POOL_SIZE = 12;
    private static ConnectionPool pool = new ConnectionPool();
    private final Logger logger = LogManager.getLogger(ConnectionPool.class);
    private Properties properties;
    private BlockingQueue<ProxyConnection> freeConnections;
    private Queue<ProxyConnection> givenAwayConnections;

    public static ConnectionPool getInstance() {
        return pool;
    }

    ConnectionPool() {
        try {
            properties = new Properties();
            properties.load(new FileReader(PROPERTIES_FILENAME));
            Class.forName(properties.getProperty(DRIVER_NAME));
            freeConnections = new LinkedBlockingQueue<>(POOL_SIZE);
            givenAwayConnections = new ArrayDeque<>(POOL_SIZE);
            for (int i = 0; i < POOL_SIZE; i++) {
                Connection connection = DriverManager.getConnection(properties.getProperty(URL),
                        properties.getProperty(LOGIN), properties.getProperty(PASSWORD));
                freeConnections.offer(new ProxyConnection(connection));
            }
        } catch (ClassNotFoundException | SQLException | IOException e) {
            logger.log(Level.FATAL,"Error while connection pool creating " + e);
            throw new RuntimeException("Error during connect to database", e);
        }
    }

    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            givenAwayConnections.offer(connection);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "Error while getting connection" + e);
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection) {
            if (givenAwayConnections.remove(connection)) {
                freeConnections.offer((ProxyConnection) connection);
            }
        } else {
            logger.log(Level.WARN, "Invalid connection to realizing");
        }
    }

    public void destroyPool() {
        try {
            for (int i = 0; i < POOL_SIZE; i++) {
                freeConnections.take().reallyClose();
            }
            deregisterDrivers();
        } catch (SQLException | InterruptedException e) {
            logger.log(Level.ERROR,"Error while close connection pool", e);
        }
    }

    private void deregisterDrivers() throws SQLException {
        while(DriverManager.getDrivers().hasMoreElements()){
            DriverManager.deregisterDriver(DriverManager.getDrivers().nextElement());
        }
    }
}
