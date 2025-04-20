package Backend;

import java.util.Date;
public class KameezShalwaar implements Measurement {
    private float trouserLength;
    private byte trouserType; // 1 for Shalwaar 2 for pajama
    private float trouserAnkle;

    private float kameezLength;
    private float sleeves;
    private byte cuffType; // 1 for Square, 2 for Round cuff
    private byte kameezType; // 1 for Square, 2 for Round Daman
    private float shoulder;
    private float neck;
    private byte collarType; // 1 for Cooper, 2 for french 3 for sherwani
    private String description;
    private int quantity;
    private Date orderDate;
    private Date deliveryDate;
    private byte status; // 1 for pending, 2 for progress, 3 for completed, 4 for delivered

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getTrouserLength() {
        return trouserLength;
    }

    public void setTrouserLength(float trouserLength) {
        this.trouserLength = trouserLength;
    }

    public byte getTrouserType() {
        return trouserType;
    }

    public void setTrouserType(byte trouserType) {
        this.trouserType = trouserType;
    }

    public float getTrouserAnkle() {
        return trouserAnkle;
    }

    public void setTrouserAnkle(float trouserAnkle) {
        this.trouserAnkle = trouserAnkle;
    }

    public float getKameezLength() {
        return kameezLength;
    }

    public void setKameezLength(float kameezLength) {
        this.kameezLength = kameezLength;
    }

    public float getSleeves() {
        return sleeves;
    }

    public void setSleeves(float sleeves) {
        this.sleeves = sleeves;
    }

    public byte getCuffType() {
        return cuffType;
    }

    public void setCuffType(byte cuffType) {
        this.cuffType = cuffType;
    }

    public byte getKameezType() {
        return kameezType;
    }

    public void setKameezType(byte kameezType) {
        this.kameezType = kameezType;
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

    public byte getCollarType() {
        return collarType;
    }

    public void setCollarType(byte collarType) {
        this.collarType = collarType;
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

    public Date getOrderDate() {
        return orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }


    public KameezShalwaar(float trouserLength, byte trouserType,
                          float trouserAnkle, float kameezLength,
                          float sleeves, byte cuffType, byte kameezType,
                          float shoulder, float neck, byte collarType,
                          byte status, String description, int quantity,
                          Date orderDate, Date deliveryDate) {
        this.trouserLength = trouserLength;
        this.trouserType = trouserType;
        this.trouserAnkle = trouserAnkle;
        this.kameezLength = kameezLength;
        this.sleeves = sleeves;
        this.cuffType = cuffType;
        this.kameezType = kameezType;
        this.shoulder = shoulder;
        this.neck = neck;
        this.collarType = collarType;
        this.status = status;
        this.description = description;
        this.quantity = quantity;
        this.deliveryDate = deliveryDate;
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "KameezShalwaar{" +
            "trouserLength=" + trouserLength +
            " trouserType=" + (trouserType == 1 ? "Shalwaar" : "Pajama") +
            " trouserAnkle=" + trouserAnkle + "\n" +
            " kameezLength=" + kameezLength +
            " sleeves=" + sleeves + "\n" +
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