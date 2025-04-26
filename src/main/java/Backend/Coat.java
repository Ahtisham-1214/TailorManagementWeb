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
        this.setChest(chest);
        this.setWaist(waist);
        this.setSleeves(sleeves);
        this.setShoulder(shoulder);
        this.setStatus(status);
        this.setDescription(description);
        this.setQuantity(quantity);
        this.setOrderDate(orderDate);
        this.setDeliveryDate(deliveryDate);
    }

    public void setChest(float chest) {
        if (chest < 1) {
            throw new IllegalArgumentException("Chest must be greater than 0");
        }
        this.chest = chest;
    }

    public void setWaist(float waist) {
        if (waist < 1) {
            throw new IllegalArgumentException("Waist must be greater than 0");
        }
        this.waist = waist;
    }

    public void setSleeves(float sleeves) {
        if (sleeves < 1) {
            throw new IllegalArgumentException("Sleeves must be greater than 0");
        }
        this.sleeves = sleeves;
    }

    public void setShoulder(float shoulder) {
        if (shoulder < 1) {
            throw new IllegalArgumentException("Shoulder must be greater than 0");
        }
        this.shoulder = shoulder;
    }

    public void setStatus(byte status) {
        if (status < 1) {
            throw new IllegalArgumentException("Status should not be 0");
        }
        this.status = status;
    }

    public void setQuantity(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("At least 1 quantity");
        }
        this.quantity = quantity;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
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
}
