package Backend;

import java.util.LinkedList;

public class Customer {
    private String name;
    private String phoneNumber;
    private LinkedList<Pant> pants;
    private LinkedList<Shirt> shirts;
    private LinkedList<Coat> coats;
    private LinkedList<KameezShalwaar> kameezShalwaars;

    @Override
    public String toString() {
        return getName() + "\t" + getPhoneNumber();
    }

    public Customer() {
        pants = new LinkedList<>();
        shirts = new LinkedList<>();
        coats = new LinkedList<>();
        kameezShalwaars = new LinkedList<>();
    }

    public void setName(String name) {
        if (!name.matches("^[A-Za-z]+([ ]?[A-Za-z]+)*$") || name.isEmpty())
            throw new IllegalArgumentException("Customer Name must contains only alphabets");
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || !phoneNumber.matches("\\d{11}")) {
            throw new IllegalArgumentException("Phone number must be exactly 11 digits and contains only numbers.");
        }
        this.phoneNumber = phoneNumber;
    }

    public Customer(String name, String phoneNumber) {
        this();
        this.setName(name);
        this.setPhoneNumber(phoneNumber);
        System.out.println("Customer " + name + " has been created"); // for debugging
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LinkedList<Pant> getPants() {
        return pants;
    }

    public LinkedList<Shirt> getShirts() {
        return shirts;
    }

    public LinkedList<Coat> getCoats() {
        return coats;
    }

    public LinkedList<KameezShalwaar> getKameezShalwaars() {
        return kameezShalwaars;
    }


}
