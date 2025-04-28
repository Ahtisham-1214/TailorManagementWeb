package Backend;

public class Item {
    String name;
    int quantity;
    float unitPrice;
    float amount;


    public Item(String name, int quantity, float unitPrice) {
        this.setName(name);
        this.setQuantity(quantity);
        this.setUnitPrice(unitPrice);
        this.setAmount(this.getQuantity() * this.getUnitPrice());
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public float getAmount() {
        return amount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
