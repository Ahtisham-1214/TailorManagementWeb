package Backend;

import java.util.Date;

public class Pant implements Measurement{
    private float waist;
    private float length;
    private byte type; // 1 for Straight fit, 2 for Cuff
    private float inseam;
    private byte status; // 1 for pending, 2 for processing, 3 for completed, 4 for delivered
    private String description;
    private int quantity;
    private Date orderDate;
    private Date deliveryDate;


    public Pant(float waist, float length, byte type, float inseam, byte status, String description, int quantity, Date orderDate, Date deliveryDate) {
        this.waist = waist;
        this.length = length;
        this.type = type;
        this.inseam = inseam;
        this.status = status;
        this.description = description;
        this.quantity = quantity;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
    }
    @Override
public String toString() {
    return "Pant Details: " +
            "\nWaist: " + waist +
            "\nLength: " + length +
            "\nType: " + (type == 1 ? "Straight Fit" : "Cuff") +
            "\nInseam: " + inseam +
            "\nStatus: " + getStatusString() +
            "\nDescription: " + description +
            "\nQuantity: " + quantity +
            "\nOrder Date: " + orderDate +
            "\nDelivery Date: " + deliveryDate;
}

private String getStatusString() {
    switch (status) {
        case 1: return "Pending";
        case 2: return "Processing";
        case 3: return "Completed";
        case 4: return "Delivered";
        default: return "Unknown";
    }
}

    public float getWaist() {
        return waist;
    }

    public void setWaist(float waist) {
        this.waist = waist;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public float getInseam() {
        return inseam;
    }

    public void setInseam(float inseam) {
        this.inseam = inseam;
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

    public int getQuantity() {
        return quantity;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
