package Backend;

import Database.KameezShalwaarDatabase;

import java.sql.SQLException;
import java.util.Date;

public class KameezShalwaar implements Measurement {
    private float trouserLength;
    private byte trouserType; // 1 for Shalwaar 2 for pajama
    private float trouserAnkle;

    private float kameezLength;
    private float sleevesLength;
    private byte cuffType; // 1 for Square, 2 for Round cuff
    private byte kameezType; // 1 for Square, 2 for Round Daman
    private float shoulder;
    private float neck;
    private float chest;
    private byte collarType; // 1 for Cooper, 2 for french 3 for sherwani
    private String description;
    private int quantity;
    private Date orderDate;
    private Date deliveryDate;
    private byte status; // 1 for pending, 2 for progress, 3 for completed, 4 for delivered

    public KameezShalwaar(float trouserLength, byte trouserType,
                          float trouserAnkle, float kameezLength, float chest,
                          float sleevesLength, byte cuffType, byte kameezType,
                          float shoulder, float neck, byte collarType,
                          byte status, String description, int quantity,
                          Date orderDate, Date deliveryDate, String phoneNumber) throws SQLException {
        this.setCollarType(collarType);
        this.setKameezLength(kameezLength);
        this.setKameezType(kameezType);
        this.setNeck(neck);
        this.setChest(chest);
        this.setDescription(description);
        this.setOrderDate(orderDate);
        this.setShoulder(shoulder);
        this.setStatus(status);
        this.setTrouserAnkle(trouserAnkle);
        this.setTrouserLength(trouserLength);
        this.setSleevesLength(sleevesLength);
        this.setTrouserType(trouserType);
        this.setCuffType(cuffType);
        this.setDeliveryDate(deliveryDate);
        this.setQuantity(quantity);

        new KameezShalwaarDatabase(this.getTrouserLength(), this.getTrouserType(), this.getTrouserAnkle(),
                this.getKameezLength(), this.getChest(), this.getSleevesLength(), this.getCuffType(), this.getKameezType(),
                this.getShoulder(), this.getNeck(), this.getCollarType(), this.getStatus(),this.getDescription(),
                this.getQuantity(), this.getOrderDate(), this.getDeliveryDate(), phoneNumber);
    }

    public void setChest(float chest) {
        if (chest < 1){
            throw new IllegalArgumentException("Chest must be greater than 0");
        }
        this.chest = chest;
    }

    public void setStatus(byte status) {
        if (status < 1) {
            throw new IllegalArgumentException("Status Should be greater than 1");
        }
        this.status = status;
    }

    public void setQuantity(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity Should at least be 1");
        }
        this.quantity = quantity;
    }

    public void setTrouserLength(float trouserLength) {
        if (trouserLength < 1) {
            throw new IllegalArgumentException("Trouser Length Should be greater than 0");
        }
        this.trouserLength = trouserLength;
    }

    public void setCuffType(byte cuffType) {
        if (cuffType < 1) {
            throw new IllegalArgumentException("Cuff Type Should be greater than 0");
        }
        this.cuffType = cuffType;
    }

    public void setTrouserType(byte trouserType) {
        if (trouserType < 1) {
            throw new IllegalArgumentException("Trouser Type Should be greater than 0");
        }
        this.trouserType = trouserType;
    }

    public void setKameezType(byte kameezType) {
        if (kameezType < 1) {
            throw new IllegalArgumentException("Kameez Type Should be greater than 0");
        }
        this.kameezType = kameezType;
    }

    public void setKameezLength(float kameezLength) {
        if (kameezLength < 1) {
            throw new IllegalArgumentException("Kameez Length Should be greater than 0");
        }
        this.kameezLength = kameezLength;
    }

    public void setSleevesLength(float sleevesLength) {
        if (sleevesLength < 1) {
            throw new IllegalArgumentException("Sleeve Length Should be greater than 0");
        }
        this.sleevesLength = sleevesLength;
    }

    public void setTrouserAnkle(float trouserAnkle) {
        if (trouserAnkle < 1) {
            throw new IllegalArgumentException("Trouser Ankle Should be greater than 0");
        }
        this.trouserAnkle = trouserAnkle;
    }

    public void setNeck(float neck) {
        if (neck < 1) {
            throw new IllegalArgumentException("Neck Should be greater than 0");
        }
        this.neck = neck;
    }

    public void setDescription(String description) {
        if (description == null || description.isEmpty())
            this.description = null;
        else
            this.description = description;
    }

    public void setShoulder(float shoulder) {
        if (shoulder < 1) {
            throw new IllegalArgumentException("Shoulder Should be greater than 0");
        }
        this.shoulder = shoulder;
    }

    public void setCollarType(byte collarType) {
        if (collarType < 1) {
            throw new IllegalArgumentException("Collar Type Should be greater than 0");
        }
        this.collarType = collarType;
    }

    public void setOrderDate(Date orderDate) {
        if (orderDate == null) {
            System.out.println("Order Date for Kameez Shalwaar was null but set today date");
            this.orderDate = java.sql.Date.valueOf(java.time.LocalDate.now());
        } else
            this.orderDate = orderDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        if (deliveryDate == null) {
            this.deliveryDate = java.sql.Date.valueOf(java.time.LocalDate.now().plusDays(7));
            System.out.println("Delivery Date for Kameez Shalwaar was null but set to " +
                    this.getDeliveryDate());
        } else
            this.deliveryDate = deliveryDate;
    }

    public byte getStatus() {
        return status;
    }

    public float getTrouserLength() {
        return trouserLength;
    }

    public byte getTrouserType() {
        return trouserType;
    }

    public float getTrouserAnkle() {
        return trouserAnkle;
    }

    public float getChest() {
        return chest;
    }

    public float getKameezLength() {
        return kameezLength;
    }

    public float getSleevesLength() {
        return sleevesLength;
    }

    public byte getCuffType() {
        return cuffType;
    }

    public byte getKameezType() {
        return kameezType;
    }

    public float getShoulder() {
        return shoulder;
    }

    public float getNeck() {
        return neck;
    }

    public byte getCollarType() {
        return collarType;
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
        return "KameezShalwaar{" +
                "trouserLength=" + trouserLength +
                " trouserType=" + (trouserType == 1 ? "Shalwaar" : "Pajama") +
                " trouserAnkle=" + trouserAnkle + "\n" +
                " kameezLength=" + kameezLength +
                " sleevesLength=" + sleevesLength + "\n" +
                " cuffType=" + (cuffType == 1 ? "Square" : "Round cuff") +
                " kameezType=" + (kameezType == 1 ? "Square Daman" : "Round Daman") +
                " shoulder=" + shoulder + "\n" +
                " neck=" + neck +
                " collarType=" + (collarType == 1 ? "Cooper" : collarType == 2 ? "French" : "Sherwani") +
                " description='" + description + '\'' +
                " quantity=" + quantity + "\n" +
                " orderDate=" + orderDate +
                " deliveryDate=" + deliveryDate +
                '}';
    }
}