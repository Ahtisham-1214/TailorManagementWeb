package Backend;

import java.util.LinkedList;


public class Receipt {
    private ShopDetails shopDetails;
    private Order order;
    private LinkedList<Item> items;
    private Prices prices;
    String shopName;
    String shopAddress;
    String shopPhone;
    String shopEmail;

    String customerName;

    public Receipt() {

    shopDetails = new ShopDetails();
    order = new Order();
    prices = new Prices();
    items = new LinkedList<>();
    order.addOrder(new Customer("Ahtisham", "12345678901"));
    order.getOrders().getFirst().getCoats().add(
            new Coat(12, 12, 12, 12, (byte) 1,
                    "hello", 10, null, null));



    if (!order.getOrders().getFirst().getCoats().isEmpty()){
        items.add(new Item("Coat",
                order.getOrders().getFirst().getCoats().getFirst().getQuantity(),
                prices.getCoatPrice()));
    }


    this.shopAddress = shopDetails.getAddress();
    this.shopName = shopDetails.getName();
    this.shopPhone = shopDetails.getPhone();
    this.shopEmail = shopDetails.getEmail();
    this.customerName = order.getOrders().getFirst().getName();
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

}
