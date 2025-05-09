package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class CoatDatabase  {
    private final Connection connection;
    public CoatDatabase(float chest, float waist, float sleeves, float shoulder, byte status,
                        String description, int quantity, Date orderDate, Date deliveryDate, String phoneNumber)
            throws SQLException {
        this.connection = DatabaseConnection.getConnection();

        insertCoat(chest, waist, sleeves, shoulder, status, description, quantity, orderDate, deliveryDate, phoneNumber);
    }

    private void insertCoat(float chest, float waist,
                            float sleeves, float shoulder, byte status,
                            String description, int quantity, Date orderDate,
                            Date deliveryDate, String phoneNumber) throws SQLException {
        try {
            String query = "Insert into coat (chest, waist, sleeves, shoulder, status, description, quantity, order_date, delivery_date, phone)" +
                    "values (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setFloat(1, chest);
            preparedStatement.setFloat(2, waist);
            preparedStatement.setFloat(3, sleeves);
            preparedStatement.setFloat(4, shoulder);
            preparedStatement.setByte(5, status);
            preparedStatement.setString(6, description);
            preparedStatement.setInt(7, quantity);
            preparedStatement.setDate(8, (java.sql.Date) orderDate);
            preparedStatement.setDate(9, (java.sql.Date) deliveryDate);
            preparedStatement.setString(10, phoneNumber);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }

    }
}
