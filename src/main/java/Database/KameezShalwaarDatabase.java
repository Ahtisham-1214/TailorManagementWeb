package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class KameezShalwaarDatabase {
    private final Connection connection;

    public KameezShalwaarDatabase(float trouserLength, byte trouserType,
                                  float trouserAnkle, float kameezLength, float chest,
                                  float sleevesLength, byte cuffType, byte kameezType,
                                  float shoulder, float neck, byte collarType,
                                  byte status, String description, int quantity,
                                  Date orderDate, Date deliveryDate, String phoneNumber) throws SQLException {
        this.connection = DatabaseConnection.getConnection();

        insertKameezShalwaar(trouserLength, trouserType, trouserAnkle,
                kameezLength, chest, sleevesLength, cuffType, kameezType,
                shoulder, neck, collarType, status, description, quantity, orderDate, deliveryDate, phoneNumber);

    }

    private void insertKameezShalwaar(float trouserLength, byte trouserType,
                                      float trouserAnkle, float kameezLength, float chest,
                                      float sleevesLength, byte cuffType, byte kameezType,
                                      float shoulder, float neck, byte collarType,
                                      byte status, String description, int quantity,
                                      Date orderDate, Date deliveryDate, String phonenumber) throws SQLException {
        try {
            String query = "Insert into kameez_shalwaar (kameez_length, kameez_type, sleeves_length," +
                    " cuff_type, shoulder, neck, chest, trouser_length, trouser_type, trouser_width, status, " +
                    "quantity, description, order_date, delivery_date, phone, collar_type )" +
                    "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setFloat(1, kameezLength);
            statement.setByte(2, kameezType);
            statement.setFloat(3, sleevesLength);
            statement.setByte(4, cuffType);
            statement.setFloat(5, shoulder);
            statement.setFloat(6, neck);
            statement.setFloat(7, chest);
            statement.setFloat(8, trouserLength);
            statement.setFloat(9, trouserType);
            statement.setFloat(10, trouserAnkle);
            statement.setByte(11, status);
            statement.setInt(12, quantity);
            statement.setString(13, description);
            statement.setDate(14, (java.sql.Date) orderDate);
            statement.setDate(15, (java.sql.Date) deliveryDate);
            statement.setString(16, phonenumber);
            statement.setByte(17, collarType);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }
}
