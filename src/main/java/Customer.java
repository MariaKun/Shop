import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//Single Responsibility Principle
public class Customer {
    private List<Order> orders = new ArrayList<>();
    private String name;
    private String email;
    private String mobile;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public Customer(String name) {
        this.name = name;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public String getName() {
        return name;
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public Order findOrder(UUID id) {
        return orders.stream()
                .filter(x -> x.getId().equals(id))
                .findFirst().get();
    }

    public boolean printOrders() {
        System.out.println("Мои заказы:");
        if (!orders.isEmpty()) {
            orders.forEach(System.out::println);
            return true;
        } else {
            System.out.println("Заказов нет");
            return false;
        }
    }
}
