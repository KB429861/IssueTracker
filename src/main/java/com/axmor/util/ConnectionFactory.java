package com.axmor.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String DRIVER = "org.h2.Driver";
    private static final String URL = "jdbc:h2:./data/test";

    private static final String USER = "sa";
    private static final String PASS = "";

    /**
     * Get a connection to the database.
     *
     * @return Connection object.
     */
    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database.", e);
        }
        return null;
    }
}
