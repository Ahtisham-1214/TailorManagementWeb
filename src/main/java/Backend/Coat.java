package Backend;

import java.util.Date;

public class Coat implements Measurement {
    private float chest;
    private float waist;
    private float sleeves;
    private float shoulder;
//    private float sleevesWidth;
    private byte status; // 1 for pending, 2 for processing, 3 for completed, 4 for delivered
    private String description;
    private int quantity;
    private Date orderDate;
    private Date deliveryDate;

    public Coat(float chest, float waist, float sleeves, float shoulder, byte status, String description, int quantity, Date orderDate, Date deliveryDate) {
        this.chest = chest;
        this.waist = waist;
        this.sleeves = sleeves;
        this.shoulder = shoulder;
//        this.sleevesWidth = sleevesWidth;
        this.status = status;
        this.description = description;
        this.quantity = quantity;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
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
                "chest=" + chest +
                ", waist=" + waist +
                ", sleeves=" + sleeves +
                ", shoulder=" + shoulder +
                ", status=" + statusString +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", orderDate=" + orderDate +
                ", deliveryDate=" + deliveryDate +
                '}';
    }

    public float getChest() {
        return chest;
    }

    public void setChest(float chest) {
        this.chest = chest;
    }

    public float getWaist() {
        return waist;
    }

    public void setWaist(float waist) {
        this.waist = waist;
    }

    public float getSleeves() {
        return sleeves;
    }

    public void setSleeves(float sleeves) {
        this.sleeves = sleeves;
    }

    public float getShoulder() {
        return shoulder;
    }

    public void setShoulder(float shoulder) {
        this.shoulder = shoulder;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}
