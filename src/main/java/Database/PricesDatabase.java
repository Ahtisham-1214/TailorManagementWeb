package Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Backend.Prices;
public class PricesDatabase {
    private Prices prices;
    public PricesDatabase() {
        this.prices = new Prices();
    }
    public Prices getPrices() {
        return prices;
    }

    public void setPrices(Prices prices) {
        this.prices = prices;
    }

   // Fetch prices from the database
   public void fetchPrices() throws SQLException {
    String query = "SELECT pant_price, coat_price, shirt_price, kameez_shalwaar_price FROM price";
    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(query);
         ResultSet resultSet = preparedStatement.executeQuery()) {
        if (resultSet.next()) {
            prices.setPantPrice(resultSet.getFloat("pant_price"));
            prices.setCoatPrice(resultSet.getFloat("coat_price"));
            prices.setShirtPrice(resultSet.getFloat("shirt_price"));
            prices.setKameezShalwaarPrice(resultSet.getFloat("kameez_shalwaar_price"));
        }
    }
}

    public void updatePrices(Connection connection) throws SQLException {
        String query = "UPDATE price SET pant_price = ?, coat_price = ?, shirt_price = ?, kameez_shalwaar_price = ? where id = 1";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setFloat(1, prices.getPantPrice());
            preparedStatement.setFloat(2, prices.getCoatPrice());
            preparedStatement.setFloat(3, prices.getShirtPrice());
            preparedStatement.setFloat(4, prices.getKameezShalwaarPrice());
            preparedStatement.executeUpdate();
        }
    }

}
