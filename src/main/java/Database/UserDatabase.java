package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDatabase {
    private final Connection connection;

    public UserDatabase() throws SQLException {
        connection = DatabaseConnection.getConnection();
    }

    public String authenticateUser(String userName, String password)
            throws Exception {

        String query = "SELECT role FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String role = resultSet.getString("role");
                    System.out.println("Authentication successful. Role: " + role);
                    return role; // Return the user's role
                }
            }
        }
        return null; // Return false if authentication fails
    }

}
