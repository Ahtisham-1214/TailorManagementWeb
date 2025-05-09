package Backend;

import Database.ShirtDatabase;

import java.sql.SQLException;
import java.util.Date;

public class Shirt extends Measurement {
    private float chest;
    private float sleeveLength;
    private float shirtLength;
    private float shoulder;
    private float neck;
    private byte collarType; // 1 for classic, 2 for standard, 3 for cooper
    private byte cuffType;  // 1 for half sleeves, 2 for Square cuff, 3 for round cuff

    public Shirt(float chest, float sleeveLength, float shirtLength, float shoulder,
                 float neck, byte collarType, byte cuffType, byte status, String description,
                 int quantity, Date orderDate, Date deliveryDate, String phoneNumber) throws SQLException {
        super(status, description, quantity, orderDate, deliveryDate);
        this.setChest(chest);
        this.setSleeveLength(sleeveLength);
        this.setShirtLength(shirtLength);
        this.setShoulder(shoulder);
        this.setNeck(neck);
        this.setCollarType(collarType);
        this.setCuffType(cuffType);
        new ShirtDatabase(this.getChest(), this.getSleeveLength(), this.getShirtLength(),
                this.getShoulder(), this.getNeck(), this.getCollarType(), this.getCuffType(), this.getStatus(),
                this.getDescription(), this.getQuantity(), this.getOrderDate(), this.getDeliveryDate(), phoneNumber);

    }


    public void setChest(float chest) {
        if (chest < 1) {
            throw new IllegalArgumentException("Chest must be greater than 0");
        } else {
            this.chest = chest;
        }
    }

    public void setSleeveLength(float sleeveLength) {
        if (sleeveLength < 1) {
            throw new IllegalArgumentException("sleeveLength must be greater than 0");
        } else {
            this.sleeveLength = sleeveLength;
        }
    }

    public void setShirtLength(float shirtLength) {
        if (shirtLength < 1) {
            throw new IllegalArgumentException("Shirt length must be greater than 0");
        } else {
            this.shirtLength = shirtLength;
        }
    }

    public void setShoulder(float shoulder) {
        if (shoulder < 1) {
            throw new IllegalArgumentException("shoulder must be greater than 0");
        } else {
            this.shoulder = shoulder;
        }
    }

    public void setNeck(float neck) {
        if (neck < 1) {
            throw new IllegalArgumentException("Neck must be greater than 0");
        } else {
            this.neck = neck;
        }
    }


    public void setCuffType(byte cuffType) {
        if (cuffType < 1) {
            throw new IllegalArgumentException("Invalid cuff Type");
        } else {
            this.cuffType = cuffType;
        }
    }

    public void setCollarType(byte collarType) {
        if (collarType < 1) {
            throw new IllegalArgumentException("Invalid Collar Type");
        } else {
            this.collarType = collarType;
        }
    }


    public byte getCuffType() {
        return cuffType;
    }

    public float getShoulder() {
        return shoulder;
    }

    public float getShirtLength() {
        return shirtLength;
    }

    public float getChest() {
        return chest;
    }

    public float getSleeveLength() {
        return sleeveLength;
    }

    public float getNeck() {
        return neck;
    }

    public byte getCollarType() {
        return collarType;
    }

    @Override
    public String toString() {
        return "Shirt{" +
                "chest=" + this.getChest() +
                ", sleeveLength=" + this.getSleeveLength() + "\n" +
                ", shirtLength=" + this.getShirtLength() +
                ", shoulder=" + this.getShoulder() +
                ", neck=" + this.getNeck() + "\n" +
                ", collarType=" + convertCollarType(this.getCollarType()) +
                ", cuffType=" + convertCuffType(this.getCuffType()) +
                ", status=" + convertStatus(this.getStatus()) + "\n" +
                ", description='" + this.getDescription() + '\'' +
                ", quantity=" + this.getQuantity() + "\n" +
                ", orderDate=" + this.getOrderDate() +
                ", deliveryDate=" + this.getDeliveryDate() +
                '}';
    }

    private String convertCollarType(byte collarType) {
        return switch (collarType) {
            case 2 -> "Standard";
            case 1 -> "Classic";
            case 3 -> "Cooper";
            default -> "Unknown";
        };
    }

    private String convertCuffType(byte cuffType) {
        return switch (cuffType) {
            case 1 -> "Half Sleeves";
            case 2 -> "Square Cuff";
            case 3 -> "Round Cuff";
            default -> "Unknown";
        };
    }

    private String convertStatus(byte status) {
        return switch (status) {
            case 1 -> "Pending";
            case 2 -> "Processing";
            case 3 -> "Completed";
            case 4 -> "Delivered";
            default -> "Unknown";
        };
    }
}
