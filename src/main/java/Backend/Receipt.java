package Backend;

import java.util.LinkedList;


public class Receipt {
    private ShopDetails shopDetails;
    private Order order;
    private LinkedList<Item> items;
    private Prices prices;

    private String shopName;
    private String shopAddress;
    private String shopPhone;
    private String shopEmail;
    private String customerName;
    private final Customer customer;

    public Receipt() {

        shopDetails = new ShopDetails();
        prices = new Prices();
        items = new LinkedList<>();
        order = new Order();
        customer = order.getCustomer();

        this.setShopAddress(shopDetails.getAddress());
        this.setShopName(shopDetails.getName());
        this.setShopPhone(shopDetails.getPhone());
        this.setShopEmail(shopDetails.getEmail());

        if (customer != null) {
            this.setCustomerName(customer.getName());

            if (getCoatQuantity() > 0)
                items.add(new Item("Coat", getCoatQuantity(), prices.getCoatPrice()));

            if (getPantQuantity() > 0)
                items.add(new Item("Pant", getPantQuantity(), prices.getPantPrice()));


            if (getShirtQuantity() > 0)
                items.add(new Item("Shirt", getShirtQuantity(), prices.getShirtPrice()));

            if (getKameezShalwaarQuantity() > 0)
                items.add(new Item("Kameez Shalwaar", getKameezShalwaarQuantity(), prices.getKameezShalwaarPrice()));

        }


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
}
