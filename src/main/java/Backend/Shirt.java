package Backend;

import java.util.Date;

public class Shirt implements Measurement {
    private float chest;
    private float sleeveLength;
    private float shirtLength;
    private float shoulder;
    private float neck;
    private byte collarType; // 1 for classic, 2 for standard, 3 for cooper
    private byte cuffType;  // 1 for half sleeves, 2 for Square cuff, 3 for round cuff
    private byte status; // 1 for pending, 2 for processing, 3 for completed, 4 for delivered
    private String description;
    private int quantity;
    private Date orderDate;
    private Date deliveryDate;


    public float getChest() {
        return chest;
    }

    public void setChest(float chest) {
        this.chest = chest;
    }

    public float getSleeveLength() {
        return sleeveLength;
    }

    public void setSleeveLength(float sleeveLength) {
        this.sleeveLength = sleeveLength;
    }

    public float getShirtLength() {
        return shirtLength;
    }

    public void setShirtLength(float shirtLength) {
        this.shirtLength = shirtLength;
    }

    public float getShoulder() {
        return shoulder;
    }

    public void setShoulder(float shoulder) {
        this.shoulder = shoulder;
    }

    public float getNeck() {
        return neck;
    }

    public void setNeck(float neck) {
        this.neck = neck;
    }

    public byte getCuffType() {
        return cuffType;
    }

    public void setCuffType(byte cuffType) {
        this.cuffType = cuffType;
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

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public byte getCollarType() {
        return collarType;
    }

    public Shirt(float chest, float sleeveLength, float shirtLength, float shoulder, float neck, byte collarType, byte cuffType, byte status, String description, int quantity, Date orderDate, Date deliveryDate) {
        this.chest = chest;
        this.sleeveLength = sleeveLength;
        this.shirtLength = shirtLength;
        this.shoulder = shoulder;
        this.neck = neck;
        this.cuffType = cuffType;
        this.collarType = collarType;
        this.status = status;
        this.description = description;
        this.quantity = quantity;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
    }

    @Override
    public String toString() {
        return "Shirt{" +
                "chest=" + chest +
                ", sleeveLength=" + sleeveLength + "\n" +
                ", shirtLength=" + shirtLength +
                ", shoulder=" + shoulder +
                ", neck=" + neck + "\n" +
                ", collarType=" + convertCollarType(collarType) +
                ", cuffType=" + convertCuffType(cuffType) +
                ", status=" + convertStatus(status) + "\n" +
                ", description='" + description + '\'' +
                ", quantity=" + quantity + "\n" +
                ", orderDate=" + orderDate +
                ", deliveryDate=" + deliveryDate +
                '}';
    }

    private String convertCollarType(byte collarType) {
        switch (collarType) {
            case 2:
                return "Standard";
            case 1:
                return "Classic";
            case 3:
                return "Cooper";
            default:
                return "Unknown";
        }
    }

    private String convertCuffType(byte cuffType) {
        switch (cuffType) {
            case 1:
                return "Half Sleeves";
            case 2:
                return "Square Cuff";
            case 3:
                return "Round Cuff";
            default:
                return "Unknown";
        }
    }

    private String convertStatus(byte status) {
        switch (status) {
            case 1:
                return "Pending";
            case 2:
                return "Processing";
            case 3:
                return "Completed";
            case 4:
                return "Delivered";
            default:
                return "Unknown";
        }
    }
}
