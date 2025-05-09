package Backend;

import java.util.Date;

public class Measurement {
    private byte status; // 1 for pending, 2 for processing, 3 for completed, 4 for delivered, 5 for Cancelled
    private String description;
    private int quantity;
    private Date orderDate;
    private Date deliveryDate;

    public Measurement(byte status, String description, int quantity, Date orderDate, Date deliveryDate){
        this.setStatus(status);
        this.setDescription(description);
        this.setQuantity(quantity);
        this.setOrderDate(orderDate);
        this.setDeliveryDate(deliveryDate);
    }

    public void setOrderDate(Date orderDate) {
        if (orderDate == null) {
            System.out.println("Order Date for " + this.getClass().getSimpleName() + " was null but set today date");
            this.orderDate = java.sql.Date.valueOf(java.time.LocalDate.now());
        } else
            this.orderDate = orderDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        if (deliveryDate == null) {
            this.deliveryDate = java.sql.Date.valueOf(java.time.LocalDate.now().plusDays(7));
            System.out.println("Delivery Date for " + this.getClass().getSimpleName() + " was null but set to " +
                    this.getDeliveryDate());
        } else
            this.deliveryDate = deliveryDate;
    }

    public void setStatus(byte status) {
        if (status < 1 || status > 5) {
            throw new IllegalArgumentException("Status should be 1 to 5");
        } else
            this.status = status;
    }

    public void setDescription(String description) {
        if (description == null || description.isEmpty())
            this.description = null;
        else
            this.description = description;
    }

    public void setQuantity(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("At least 1 quantity");
        }
        this.quantity = quantity;
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
}