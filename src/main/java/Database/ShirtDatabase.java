package Database;

import Backend.Shirt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class ShirtDatabase {
    private final Connection connection;

    public ShirtDatabase(float chest, float sleeveLength, float shirtLength, float shoulder,
                         float neck, byte collarType, byte cuffType, byte status, String description,
                         int quantity, Date orderDate, Date deliveryDate, String phoneNumber) throws SQLException {

        connection = DatabaseConnection.getConnection();
        insertShirt(chest, sleeveLength, shirtLength, shoulder, neck,
                collarType, cuffType, status, description, quantity, orderDate, deliveryDate, phoneNumber);
    }

    private void insertShirt(float chest, float sleeveLength, float shirtLength, float shoulder,
                             float neck, byte collarType, byte cuffType, byte status, String description,
                             int quantity, Date orderDate, Date deliveryDate, String phoneNumber) throws SQLException {

        try {
            String query = "Insert into shirt (chest, neck, shoulder, sleeves_length,  shirt_length, cuff_type, " +
                    "collar_type, quantity, status, description, order_date, delivery_date, phone)" +
                    "values (?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setFloat(1, chest);
            statement.setFloat(2, neck);
            statement.setFloat(3, shoulder);
            statement.setFloat(4, sleeveLength);
            statement.setFloat(5, shirtLength);
            statement.setByte(6, cuffType);
            statement.setByte(7, collarType);
            statement.setInt(8, quantity);
            statement.setByte(9, status);
            statement.setString(10, description);
            statement.setDate(11, (java.sql.Date) orderDate);
            statement.setDate(12, (java.sql.Date) deliveryDate);
            statement.setString(13, phoneNumber);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }

    }
}
