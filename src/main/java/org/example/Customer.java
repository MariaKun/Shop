package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//Single Responsibility Principle
public class Customer {
    private List<Order> orders = new ArrayList<>();
    private String name;

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
}
