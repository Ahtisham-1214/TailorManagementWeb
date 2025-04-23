package Backend;
import Database.PricesDatabase;
public class Prices {
    private float pantPrice;
    private float coatPrice;
    private float shirtPrice;
    private float kameezShalwaarPrice;
    private PricesDatabase pricesDatabase;
    public Prices(){
        // Default constructor
        pricesDatabase = new PricesDatabase();
        this.setPantPrice(pricesDatabase.getPantPrice());
        this.setCoatPrice(pricesDatabase.getCoatPrice());
        this.setShirtPrice(pricesDatabase.getShirtPrice());
        this.setKameezShalwaarPrice(pricesDatabase.getKameezShalwaarPrice());
    }
    public Prices(float pantPrice, float shirtPrice, float coatPrice, float kameezShalwaarPrice) {
        this.setPantPrice(pantPrice);
        this.setShirtPrice(shirtPrice);
        this.setCoatPrice(coatPrice);
        this.setKameezShalwaarPrice(kameezShalwaarPrice);
        pricesDatabase = new PricesDatabase(this.getPantPrice(), this.getShirtPrice(), this.getCoatPrice(), this.getKameezShalwaarPrice());
    }

    public float getPantPrice() {
        return pantPrice;
    }

    public void setPantPrice(float pantPrice) {
        if (pantPrice < 1) { // Adjusted to match your frontend validation
            throw new IllegalArgumentException("Price cannot be less than 1");
        }
        this.pantPrice = pantPrice;
    }

    public float getCoatPrice() {
        return coatPrice;
    }

    public void setCoatPrice(float coatPrice) {
        if (coatPrice < 1) { // Adjusted to match your frontend validation
            throw new IllegalArgumentException("Price cannot be less than 1");
        }
        this.coatPrice = coatPrice;
    }

    public float getShirtPrice() {
        return shirtPrice;
    }

    public void setShirtPrice(float shirtPrice) {
        if (shirtPrice < 1) { // Adjusted to match your frontend validation
            throw new IllegalArgumentException("Price cannot be less than 1");
        }
        this.shirtPrice = shirtPrice;
    }

    public float getKameezShalwaarPrice() {
        return kameezShalwaarPrice;
    }

    public void setKameezShalwaarPrice(float kameezShalwaarPrice) {
        if (kameezShalwaarPrice < 1) { // Adjusted to match your frontend validation
            throw new IllegalArgumentException("Price cannot be less than 1");
        }
        this.kameezShalwaarPrice = kameezShalwaarPrice;
    }

    @Override
    public String toString() {
        return "Prices{" +
                "pantPrice=" + pantPrice +
                ", coatPrice=" + coatPrice +
                ", shirtPrice=" + shirtPrice +
                ", kameezShalwaarPrice=" + kameezShalwaarPrice +
                '}';
    }
}
