package Backend;

import Database.CoatDatabase;

import java.sql.SQLException;
import java.util.Date;

public class Coat implements Measurement {
    private float chest;
    private float waist;
    private float sleeves;
    private float shoulder;
    //    private float sleevesWidth;
    private byte status; // 1 for pending, 2 for processing, 3 for completed, 4 for delivered, 5 for Cancelled
    private String description;
    private int quantity;
    private Date orderDate;
    private Date deliveryDate;

    public Coat(float chest, float waist, float sleeves,
                float shoulder, byte status, String description,
                int quantity, Date orderDate, Date deliveryDate, String phoneNumber) throws SQLException {
        this.setChest(chest);
        this.setWaist(waist);
        this.setSleeves(sleeves);
        this.setShoulder(shoulder);
        this.setStatus(status);
        this.setDescription(description);
        this.setQuantity(quantity);
        this.setOrderDate(orderDate);
        this.setDeliveryDate(deliveryDate);
        new CoatDatabase(this.getChest(), this.getWaist(), this.getSleeves(), this.getShoulder(), this.getStatus(),
                this.getDescription(), this.getQuantity(), this.getOrderDate(), this.getDeliveryDate(), phoneNumber);
    }

    public void setChest(float chest) {
        if (chest < 1) {
            throw new IllegalArgumentException("Chest must be greater than 0");
        } else
            this.chest = chest;
    }

    public void setWaist(float waist) {
        if (waist < 1) {
            throw new IllegalArgumentException("Waist must be greater than 0");
        } else
            this.waist = waist;
    }

    public void setSleeves(float sleeves) {
        if (sleeves < 1) {
            throw new IllegalArgumentException("Sleeves must be greater than 0");
        } else
            this.sleeves = sleeves;
    }

    public void setShoulder(float shoulder) {
        if (shoulder < 1) {
            throw new IllegalArgumentException("Shoulder must be greater than 0");
        }
        this.shoulder = shoulder;
    }

    public void setStatus(byte status) {
        if (status < 1 || status > 5) {
            throw new IllegalArgumentException("Status should be 1 to 5");
        } else
            this.status = status;
    }

    public void setQuantity(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("At least 1 quantity");
        }
        this.quantity = quantity;
    }

    public void setOrderDate(Date orderDate) {
        if (orderDate == null) {
            System.out.println("Order Date for Coat was null but set today date");
            this.orderDate = java.sql.Date.valueOf(java.time.LocalDate.now());
        } else
            this.orderDate = orderDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        if (deliveryDate == null) {
            this.deliveryDate = java.sql.Date.valueOf(java.time.LocalDate.now().plusDays(7));
            System.out.println("Delivery Date for Coat was null but set to " +
                    this.getDeliveryDate());
        } else
            this.deliveryDate = deliveryDate;
    }

    public void setDescription(String description) {
        if (description == null || description.isEmpty())
            this.description = null;
        else
            this.description = description;
    }

    public float getChest() {
        return chest;
    }

    public float getWaist() {
        return waist;
    }

    public float getSleeves() {
        return sleeves;
    }

    public float getShoulder() {
        return shoulder;
    }

    public byte getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public String getClassName() {
        return this.getClass().getSimpleName();
    }

    // for testing
    public Coat() {

    }

    @Override
    public String toString() {
        String statusString;
        switch (status) {
            case 1 -> statusString = "Pending";
            case 2 -> statusString = "Processing";
            case 3 -> statusString = "Completed";
            case 4 -> statusString = "Delivered";
            default -> statusString = "Unknown";
        }
        return "Coat{" +
                "chest=" + this.getChest() +
                ", waist=" + this.getWaist() +
                ", sleeves=" + this.getSleeves() +
                ", shoulder=" + this.getShoulder() +
                ", status=" + this.getStatus() +
                ", description='" + this.getDescription() + '\'' +
                ", quantity=" + this.getQuantity() +
                ", orderDate=" + this.getOrderDate() +
                ", deliveryDate=" + this.getDeliveryDate() +
                '}';
    }

    public static void main(String[] args) {
        // For testing
//        Coat coat = new Coat();
//        coat.setOrderDate(null);
//        coat.setDeliveryDate(null);
//        System.out.println(coat.getOrderDate());
//        System.out.println(coat.getDeliveryDate());
//        coat.setStatus((byte) 5);
    }
}
