package Backend;

public class Prices {
    float pantPrice;
    float coatPrice;
    float shirtPrice;
    float kameezShalwaarPrice;

    public Prices(){
        // Default constructor
    }
    public Prices(float pantPrice, float coatPrice, float shirtPrice, float kameezShalwaarPrice) {
        this.pantPrice = pantPrice;
        this.coatPrice = coatPrice;
        this.shirtPrice = shirtPrice;
        this.kameezShalwaarPrice = kameezShalwaarPrice;
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
