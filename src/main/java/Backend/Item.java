package Backend;

public class Item {
    String name;
    int quantity;
    float unitPrice;
    float amount;


    public Item(String name, int quantity, float unitPrice) {
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.amount = unitPrice * quantity;
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
}
