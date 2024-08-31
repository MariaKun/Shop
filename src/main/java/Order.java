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
        StringBuilder str = new StringBuilder();
        int sum = 0;
        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
            str.append(entry.getKey().toString() + " Количество = " + entry.getValue());
            sum = sum + entry.getKey().getPrice() * entry.getValue();
        }

        return "ID = " + id +
                ", \nТовары = " + str +
                ", \nадрес = " + address +
                ", \nсумма = " + sum;
    }
}
