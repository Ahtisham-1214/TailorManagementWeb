package Backend;

import java.util.Date;

public class Pant implements Measurement {
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
        this.setWaist(waist);
        this.setLength(length);
        this.setType(type);
        this.setInseam(inseam);
        this.setStatus(status);
        this.setDescription(description);
        this.setOrderDate(orderDate);
        this.setDeliveryDate(deliveryDate);
        this.setQuantity(quantity);
    }

    public void setWaist(float waist) {
        if (waist < 1) {
            throw new IllegalArgumentException("Waist must be greater than 0");
        } else {
            this.waist = waist;
        }
    }

    public void setLength(float length) {
        this.length = length;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public void setInseam(float inseam) {
        if (inseam < 1) {
            throw new IllegalArgumentException("Inseam at least 1");
        } else {
            this.inseam = inseam;
        }
    }

    public void setStatus(byte status) {
        if (status < 1 || status > 5) {
            throw new IllegalArgumentException("Status must be 1 to 5.");
        } else {
            this.status = status;
        }
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantity(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        } else {
            this.quantity = quantity;
        }
    }

    public void setOrderDate(Date orderDate) {
        if (orderDate == null) {
            System.out.println("Order Date for Pant was null but set today date");
            this.orderDate = java.sql.Date.valueOf(java.time.LocalDate.now());
        } else
            this.orderDate = orderDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        if (deliveryDate == null) {
            this.deliveryDate = java.sql.Date.valueOf(java.time.LocalDate.now().plusDays(7));
            System.out.println("Delivery Date for Pant was null but set to " +
                    this.getDeliveryDate());
        } else
            this.deliveryDate = deliveryDate;
    }

    public float getWaist() {
        return waist;
    }

    public float getLength() {
        return length;
    }

    public byte getType() {
        return type;
    }

    public float getInseam() {
        return inseam;
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

    private String getStatusString() {
        return switch (this.getStatus()) {
            case 1 -> "Pending";
            case 2 -> "Processing";
            case 3 -> "Completed";
            case 4 -> "Delivered";
            default -> "Unknown";
        };
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
}
