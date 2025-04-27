package Backend;

public class Receipt {
    private ShopDetails shopDetails;
    private Order order;
    String shopName;
    String shopAddress;
    String shopPhone;
    String shopEmail;

    String customerName;

    public Receipt() {
    shopDetails = new ShopDetails();
    order = new Order();
    order.addOrder(new Customer("Ahtisham", "12345678901"));
    order.getOrders().getFirst().getCoats().add(
            new Coat(12, 12, 12, 12, (byte) 1,
                    "hello", 10, null, null));


    this.shopAddress = shopDetails.getAddress();
    this.shopName = shopDetails.getName();
    this.shopPhone = shopDetails.getPhone();
    this.shopEmail = shopDetails.getEmail();
    this.customerName = order.getOrders().getFirst().getName();
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
