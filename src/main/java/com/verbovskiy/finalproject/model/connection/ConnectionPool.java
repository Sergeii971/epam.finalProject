package com.verbovskiy.finalproject.model.connection;

import com.verbovskiy.finalproject.exception.ConnectionDatabaseException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ConnectionPool {
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";
    private static final String URL = "jdbc:mysql://localhost/finalProject?useUnicode=true&serverTimezone=UTC";
    private static final int POOL_SIZE = 30;
    private static ConnectionPool instance;
    private static volatile boolean instanceIsCreated;

    private BlockingQueue<ProxyConnection> freeConnections;
    private Queue<ProxyConnection> givenAwayConnections;

    public static ConnectionPool getInstance() {
        if (!instanceIsCreated) {
            synchronized (ConnectionPool.class) {
                if (!instanceIsCreated) {
                    instance = new ConnectionPool();
                    instanceIsCreated = true;
                }
            }
        }
        return instance;
    }

    ConnectionPool() {
        try {
            Class.forName(DRIVER_NAME);
            freeConnections = new LinkedBlockingDeque<>(POOL_SIZE);
            givenAwayConnections = new ArrayDeque<>(POOL_SIZE);
            for (int i = 0; i < POOL_SIZE; i++) {
                freeConnections.offer(new ProxyConnection(DriverManager.getConnection(URL, LOGIN, PASSWORD)));
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error while connection pool creating " + e);
        }
    }

    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            givenAwayConnections.offer(connection);
        } catch (InterruptedException e) {
            System.out.println("Error while getting connection" + e);

        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (connection.getClass() == ProxyConnection.class) {
            givenAwayConnections.remove(connection);
            freeConnections.offer((ProxyConnection) connection);
        }
    }

    public void destroyPool() throws ConnectionDatabaseException {
        try {
            for (int i = 0; i < POOL_SIZE; i++) {
                freeConnections.take().reallyClose();
            }
            deregisterDrivers();
        } catch (SQLException | InterruptedException e) {
            throw new ConnectionDatabaseException("Error while close connection pool", e);
        }
    }

    private void deregisterDrivers() throws SQLException {
        while(DriverManager.getDrivers().hasMoreElements()){
            DriverManager.deregisterDriver(DriverManager.getDrivers().nextElement());
        }
    }
}
