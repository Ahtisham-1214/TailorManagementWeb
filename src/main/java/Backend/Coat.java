package Backend;

import Database.CoatDatabase;

import java.sql.SQLException;
import java.util.Date;

public class Coat extends Measurement {
    private float chest;
    private float waist;
    private float sleeves;
    private float shoulder;
    //    private float sleevesWidth;

    public Coat(float chest, float waist, float sleeves,
                float shoulder, byte status, String description,
                int quantity, Date orderDate, Date deliveryDate, String phoneNumber) throws SQLException {
        super(status, description, quantity, orderDate, deliveryDate);
        this.setChest(chest);
        this.setWaist(waist);
        this.setSleeves(sleeves);
        this.setShoulder(shoulder);
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


    @Override
    public String toString() {
        String statusString;
        switch (this.getStatus()) {
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
