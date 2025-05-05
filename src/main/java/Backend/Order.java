package Backend;

import java.util.LinkedList;

public class Order {
    private static LinkedList<Customer> orders = new LinkedList<>();
    private static int pointer = -1;

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
        Order.pointer++;
    }

    public void addCoatToOrder(Coat coat) {
        try {
            if (!this.getOrders().isEmpty()) {
                this.getOrders().get(Order.pointer).getCoats().add(coat);
            }

        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void addShirtToOrder(Shirt shirt) {
        try {
            if (!this.getOrders().isEmpty()) {
                this.getOrders().get(Order.pointer).getShirts().add(shirt);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void addPantsToOrder(Pant pant) {
        try {
            if (!this.getOrders().isEmpty()) {
                this.getOrders().get(Order.pointer).getPants().add(pant);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void addKameezShalwaarToOrder(KameezShalwaar kameezShalwaar) {
        try {
            if (!this.getOrders().isEmpty()) {
                this.getOrders().get(Order.pointer).getKameezShalwaars().add(kameezShalwaar);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public Customer getCustomer() {
        if (!this.getOrders().isEmpty()) {
            return this.getOrders().get(Order.pointer);
        }else {
            return null;
        }
    }

}
