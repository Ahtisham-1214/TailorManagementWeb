package Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PricesDatabase {

    private float pantPrice;
    private float shirtPrice;
    private float coatPrice;
    private float kameezShalwaarPrice;

    public PricesDatabase() {
        fetchPrices();
    }

    public PricesDatabase(float pantPrice, float shirtPrice, float coatPrice, float kameezShalwaarPrice) {
        this.pantPrice = pantPrice;
        this.shirtPrice = shirtPrice;
        this.coatPrice = coatPrice;
        this.kameezShalwaarPrice = kameezShalwaarPrice;
        updatePrices();
    }
   // Fetch prices from the database
   private void fetchPrices() {
    String query = "SELECT pant_price, coat_price, shirt_price, kameez_shalwaar_price FROM price";
    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(query);
         ResultSet resultSet = preparedStatement.executeQuery()) {
        if (resultSet.next()) {
            this.setPantPrice(resultSet.getFloat("pant_price"));
            this.setCoatPrice(resultSet.getFloat("coat_price"));
            this.setShirtPrice(resultSet.getFloat("shirt_price"));
            this.setKameezShalwaarPrice(resultSet.getFloat("kameez_shalwaar_price"));
        }
    }catch (SQLException e) {
        throw new RuntimeException(e);
    }
}

    private void updatePrices() {
        // SQL query to update prices in the database
        String query = "UPDATE price SET pant_price = ?, coat_price = ?, shirt_price = ?, kameez_shalwaar_price = ? WHERE id = 1";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set the values for the placeholders in the query
            preparedStatement.setFloat(1, this.getPantPrice());
            preparedStatement.setFloat(2, this.getCoatPrice());
            preparedStatement.setFloat(3, this.getShirtPrice());
            preparedStatement.setFloat(4, this.getKameezShalwaarPrice());

            // Execute the update query
            preparedStatement.executeUpdate();

            System.out.println("Prices updated successfully in the database.");
        } catch (SQLException e) {
            // Handle SQL exceptions
            e.printStackTrace();
            System.err.println("Failed to update prices in the database: " + e.getMessage());
        }
    }

    public float getPantPrice() {
        return pantPrice;
    }

    public void setPantPrice(float pantPrice) {
        this.pantPrice = pantPrice;
    }

    public float getShirtPrice() {
        return shirtPrice;
    }

    public void setShirtPrice(float shirtPrice) {
        this.shirtPrice = shirtPrice;
    }

    public float getCoatPrice() {
        return coatPrice;
    }

    public void setCoatPrice(float coatPrice) {
        this.coatPrice = coatPrice;
    }

    public float getKameezShalwaarPrice() {
        return kameezShalwaarPrice;
    }

    public void setKameezShalwaarPrice(float kameezShalwaarPrice) {
        this.kameezShalwaarPrice = kameezShalwaarPrice;
    }
}
