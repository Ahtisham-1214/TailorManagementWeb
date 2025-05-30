package Backend;

import java.util.LinkedList;


public class Receipt {
    private final LinkedList<Item> items;
    private final Customer customer;

    private String shopName;
    private String shopAddress;
    private String shopPhone;
    private String shopEmail;
    private String customerName;
    private float subTotal;
    private float tax;
    private float total;

    public Receipt() {

        ShopDetails shopDetails = new ShopDetails();
        Prices prices = new Prices();
        items = new LinkedList<>();
        Order order = new Order();
        customer = order.getCustomer();

        subTotal = 0;
        tax = 0;
        total = 0;

        this.setShopAddress(shopDetails.getAddress());
        this.setShopName(shopDetails.getName());
        this.setShopPhone(shopDetails.getPhone());
        this.setShopEmail(shopDetails.getEmail());

        if (customer != null) {
            this.setCustomerName(customer.getName());

            if (getCoatQuantity() > 0) {
                items.add(new Item("Coat", getCoatQuantity(), prices.getCoatPrice()));
                this.setSubTotal(getCoatQuantity() * prices.getCoatPrice());
            }
            if (getPantQuantity() > 0) {
                items.add(new Item("Pant", getPantQuantity(), prices.getPantPrice()));
                this.setSubTotal(getPantQuantity() * prices.getPantPrice());
            }

            if (getShirtQuantity() > 0) {
                items.add(new Item("Shirt", getShirtQuantity(), prices.getShirtPrice()));
                this.setSubTotal(getShirtQuantity() * prices.getShirtPrice());
            }
            if (getKameezShalwaarQuantity() > 0) {
                items.add(new Item("Kameez Shalwaar", getKameezShalwaarQuantity(), prices.getKameezShalwaarPrice()));
                this.setSubTotal(getKameezShalwaarQuantity() * prices.getKameezShalwaarPrice());
            }
        }

        this.setTotal(this.getSubTotal());


    }


    private int getCoatQuantity() {
//        LinkedList<Coat> coats = order.getOrders().get(Order.getPointer()).getCoats(); //Previous approach,
//        it was good but hence this approach proved I'm a programmer
        int coatQuantity = 0;
        for (Coat coat : customer.getCoats()) {
            coatQuantity += coat.getQuantity();
        }
        return coatQuantity;
    }

    private int getShirtQuantity() {
//        LinkedList<Shirt> shirts = order.getOrders().get(Order.getPointer()).getShirts();
        int shirtQuantity = 0;
        for (Shirt shirt : customer.getShirts()) {
            shirtQuantity += shirt.getQuantity();
        }
        return shirtQuantity;
    }

    private int getPantQuantity() {
//        LinkedList<Pant> pants = order.getOrders().get(Order.getPointer()).getPants();
        int pantQuantity = 0;
        for (Pant pant : customer.getPants()) {
            pantQuantity += pant.getQuantity();
        }
        return pantQuantity;
    }

    private int getKameezShalwaarQuantity() {
//        LinkedList<KameezShalwaar> kameezShalwaars = order.getOrders().get(Order.getPointer()).getKameezShalwaars();
        int kameezShalwaarQuantity = 0;

        for (KameezShalwaar kameezShalwaar : customer.getKameezShalwaars()) {
            kameezShalwaarQuantity += kameezShalwaar.getQuantity();
        }
        return kameezShalwaarQuantity;
    }

    public LinkedList<Item> getItems() {
        return items;
    }

    public String getShopName() {
        return shopName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public String getShopPhone() {
        return shopPhone;
    }

    public String getShopEmail() {
        return shopEmail;
    }

    public String getCustomerName() {
        return customerName;
    }

    private void setShopName(String shopName) {
        this.shopName = shopName;
    }

    private void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    private void setShopPhone(String shopPhone) {
        this.shopPhone = shopPhone;
    }

    private void setShopEmail(String shopEmail) {
        this.shopEmail = shopEmail;
    }

    private void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public float getSubTotal() {
        return subTotal;
    }

    public float getTax() {
        return tax;
    }

    public float getTotal() {
        return total;
    }

    private void setSubTotal(float subTotal) {
        this.subTotal += subTotal;
    }

    private void setTotal(float total) {
        this.total += total;
    }
}
