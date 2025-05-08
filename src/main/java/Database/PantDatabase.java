package Database;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class PantDatabase {
    private final Connection connection;


    public PantDatabase(float waist, float length, byte type, float inseam, byte status,
                        String description, int quantity,  Date orderDate, Date deliveryDate, String phoneNumber) throws SQLException {
        this.connection = DatabaseConnection.getConnection();
        insertPant(waist, length, type, inseam, status, description, quantity, orderDate, deliveryDate, phoneNumber);
    }

    private void insertPant(float waist, float length, byte type, float inseam, byte status, String description,
                            int quantity, Date orderDate, Date deliveryDate, String phoneNumber) throws SQLException {
        try {
            String query = "INSERT INTO pant (waist, length, inseam, type, status, quantity, description, order_date, delivery_date, phone)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setFloat(1, (waist));
            ps.setFloat(2, length);
            ps.setFloat(3, inseam);
            ps.setByte(4, type);
            ps.setByte(5, status);
            ps.setInt(6, quantity);
            ps.setString(7, description);
            ps.setDate(8, (java.sql.Date) orderDate);
            ps.setDate(9, (java.sql.Date) deliveryDate);
            ps.setString(10, phoneNumber);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }

    }

    public static void main(String[] args) {

    }

}
