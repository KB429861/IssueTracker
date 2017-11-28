package com.axmor.user;

import com.axmor.util.DatabaseHelper;

import java.sql.*;

public class UserDaoImpl implements UserDao {

    private User extractUserFromResultSet(ResultSet rs) {
        try {
            User user = new User();
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUser(String username) {
        try (Connection connection = DatabaseHelper.getConnection()) {
            if (connection != null) {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM users WHERE username='" + username + "'");
                if (rs.next()) {
                    return extractUserFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insertUser(User user) {
        try (Connection connection = DatabaseHelper.getConnection()) {
            if (connection != null) {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO users VALUES (?, ?)");
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getPassword());
                int result = ps.executeUpdate();
                if (result == 1) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
