import Products.Product;

import java.util.Map;
import java.util.UUID;

//Single Responsibility Principle
public class Order {

    private Map<Product, Integer> cart;
    private String address;
    private UUID id;

    public Order(Map<Product, Integer> cart, String address) {
        this.id = UUID.randomUUID();
        this.address = address;
        this.cart = cart;
    }

    public UUID getId() {
        return id;
    }

    public Map<Product, Integer> getCart() {
        return cart;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Order{" +
                "cart=" + cart +
                ", address='" + address + '\'' +
                ", id=" + id +
                '}';
    }
}
