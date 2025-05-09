package Backend;

import java.sql.SQLException;
import java.util.Date;
import Database.PantDatabase;
public class Pant extends Measurement {
    private float waist;
    private float length;
    private byte type; // 1 for Straight fit, 2 for Cuff
    private float inseam;
    private String phoneNumber;


    public Pant(float waist, float length, byte type, float inseam, byte status, String description, int quantity, Date orderDate, Date deliveryDate, String phoneNumber) throws SQLException {
        super(status, description, quantity, orderDate, deliveryDate);
        this.setWaist(waist);
        this.setLength(length);
        this.setType(type);
        this.setInseam(inseam);
        this.setPhoneNumber(phoneNumber);
        new PantDatabase(this.getWaist(), this.getLength(), this.getType(), this.getInseam(), this.getStatus(),
                this.getDescription(), this.getQuantity(), this.getOrderDate(), this.getDeliveryDate(), phoneNumber);
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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


    public String getPhoneNumber() {
        return phoneNumber;
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
                "\nDescription: " + this.getDescription() +
                "\nQuantity: " + this.getQuantity() +
                "\nOrder Date: " + this.getOrderDate() +
                "\nDelivery Date: " + this.getDeliveryDate();
    }
}
