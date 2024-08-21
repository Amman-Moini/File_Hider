

package dao;

import db.MyConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    // Method to check if a user exists based on their email
    public static boolean isExists(String email) throws SQLException {
        Connection connection = MyConnection.getConnection();

        // Check if connection is null before proceeding
        if (connection == null) {
            throw new SQLException("Connection to the database is null.");
        }

        String query = "SELECT email FROM users WHERE email = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();

        // If the email exists in the database, return true
        if (rs.next()) {
            return true;
        }
        return false;
    }

    // Method to save a new user to the database
    public static int saveUser(User user) throws SQLException {
        Connection connection = MyConnection.getConnection();

        // Check if connection is null before proceeding
        if (connection == null) {
            throw new SQLException("Connection to the database is null.");
        }

        String query = "INSERT INTO users (name, email) VALUES (?, ?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, user.getName());
        ps.setString(2, user.getEmail());

        // Execute the update and return the result
        return ps.executeUpdate();
    }
}
