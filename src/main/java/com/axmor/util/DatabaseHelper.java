package com.axmor.util;

import org.h2.tools.RunScript;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {

    private static final String DRIVER = "org.h2.Driver";
    private static final String URL = "jdbc:h2:./data/test";

    private static final String USER = "sa";
    private static final String PASS = "";

    private static final String SCRIPT = "./src/main/java/com/axmor/database/tables.sql";

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

    /**
     * Create database tables.
     */
    public static void createTables() {
        Connection connection = getConnection();
        if (connection != null) {
            try {
                RunScript.execute(connection, new FileReader(SCRIPT));
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                throw new RuntimeException("Can't open SQL script.", e);
            }
        }
    }
}
