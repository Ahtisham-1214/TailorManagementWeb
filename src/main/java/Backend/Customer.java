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
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Customer(String name, String phoneNumber) {
        this();
        this.name = name;
        this.phoneNumber = phoneNumber;
//        pants = new LinkedList<>();
//        shirts = new LinkedList<>();
//        coats = new LinkedList<>();
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
