package Database;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShopDetailsDatabase {
    String shopName;
    String shopAddress;
    String shopPhoneNumber;
    String shopEmailAddress;

    public ShopDetailsDatabase() {
        fetchShopDetails();
    }

    public ShopDetailsDatabase(String shopName, String shopAddress, String shopPhoneNumber, String shopEmailAddress){
        this.shopName = shopName;
        this.shopAddress = shopAddress;
        this.shopPhoneNumber = shopPhoneNumber;
        this.shopEmailAddress = shopEmailAddress;
        updateShopDetails();
    }
    private void fetchShopDetails() {
        String query = "SELECT shop_name, address, phone_no, email FROM details where id = 1";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                this.setShopName(resultSet.getString("shop_name"));
                this.setShopAddress(resultSet.getString("address"));
                this.setShopPhoneNumber(resultSet.getString("phone_no"));
                this.setShopEmailAddress(resultSet.getString("email"));

                System.out.println("Shop details fetched successfully");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateShopDetails() {
        String query = "UPDATE details SET shop_name = ?, address = ?, phone_no = ?, email = ? WHERE id = 1";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, this.getShopName());
            preparedStatement.setString(2, this.getShopAddress());
            preparedStatement.setString(3, this.getShopPhoneNumber());
            preparedStatement.setString(4, this.getShopEmailAddress());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Shop details updated successfully");
            } else {
                System.out.println("No records updated.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getShopPhoneNumber() {
        return shopPhoneNumber;
    }

    public void setShopPhoneNumber(String shopPhoneNumber) {
        this.shopPhoneNumber = shopPhoneNumber;
    }

    public String getShopEmailAddress() {
        return shopEmailAddress;
    }

    public void setShopEmailAddress(String shopEmailAddress) {
        this.shopEmailAddress = shopEmailAddress;
    }
}
