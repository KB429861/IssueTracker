package com.axmor.util;

import org.h2.jdbcx.JdbcConnectionPool;
import org.h2.tools.RunScript;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseHelper {

    private static final String URL = "jdbc:h2:./data/test";

    private static final String USER = "sa";
    private static final String PASS = "";

    private static final String SCRIPT = "./src/main/resources/sql/create-db.sql";

    private static JdbcConnectionPool connectionPool = null;

    /**
     * Get a connection to the database.
     *
     * @return Connection object.
     */
    public static Connection getConnection() {
        if (connectionPool == null) {
            connectionPool = JdbcConnectionPool.create(URL, USER, PASS);
        }
        try {
            return connectionPool.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Create database tables.
     */
    public static void createTables() {
        try (Connection connection = getConnection()) {
            if (connection != null) {
                RunScript.execute(connection, new FileReader(SCRIPT));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find SQL script.", e);
        }
    }
}
