package Backend;
import Database.ShopDetailsDatabase;
public class ShopDetails {
    String name;
    String address;
    String phone;
    String email;
    ShopDetailsDatabase shopDetailsDatabase;
    public ShopDetails() {
        shopDetailsDatabase = new ShopDetailsDatabase();
        this.name = shopDetailsDatabase.getShopName();
        this.address = shopDetailsDatabase.getShopAddress();
        this.phone = shopDetailsDatabase.getShopPhoneNumber();
        this.email = shopDetailsDatabase.getShopEmailAddress();
    }
    public ShopDetails(String name, String address, String phone, String email) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        shopDetailsDatabase = new ShopDetailsDatabase(this.name, this.address, this.phone, this.email);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
