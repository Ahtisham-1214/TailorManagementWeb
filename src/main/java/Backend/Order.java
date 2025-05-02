package Backend;

import java.util.LinkedList;

public class Order {
    private static LinkedList<Customer> orders = new LinkedList<>();

    public Order() {
//        orders = new LinkedList<>();
    }

    public LinkedList<Customer> getOrders() {
        return orders;
    }

    public void setOrders(LinkedList<Customer> orders) {
        Order.orders = orders;
    }

    public void addOrder(Customer customer) {
        this.getOrders().add(customer);
    }

    public void addCoatToOrder(Coat coat) {
        try {
            if (!this.getOrders().isEmpty()) {
                this.getOrders().getFirst().getCoats().add(coat);
            }

        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void addShirtToOrder(Shirt shirt) {
        try {
            if (!this.getOrders().isEmpty()) {
                this.getOrders().getFirst().getShirts().add(shirt);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void addPantsToOrder(Pant pant) {
        try {
            if (!this.getOrders().isEmpty()) {
                this.getOrders().getFirst().getPants().add(pant);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void addKameezShalwaarToOrder(KameezShalwaar kameezShalwaar) {
        try {
            if (!this.getOrders().isEmpty()) {
                this.getOrders().getFirst().getKameezShalwaars().add(kameezShalwaar);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public String getCustomerName() {
        if (!this.getOrders().isEmpty()) {
            return this.getOrders().getFirst().getName();
        }else {
            return null;
        }
    }


}
