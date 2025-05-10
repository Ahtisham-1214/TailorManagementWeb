package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerDatabase {
    private final Connection connection;

    public CustomerDatabase(String name, String phone) throws SQLException {
        this.connection = DatabaseConnection.getConnection();
        insertCustomer(name, phone);
    }



    private void insertCustomer(String name, String phone) throws SQLException {
        try {
            String query = "INSERT INTO customer (name, phone) VALUES (?, ?) ON DUPLICATE KEY UPDATE name = VALUES(name)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, phone);

            statement.executeUpdate();
            if (statement.getUpdateCount() == 0) {
                throw new SQLException("Insert Customer Failed");
            }
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
    }

    // For Testing
    public static void main(String[] args) {
//        CustomerDatabase cd = new CustomerDatabase();
//
//        try {
//            System.out.println(cd.insertCustomer("Ahtisham", "03448143397"));
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }
}
