package org.example;

import org.example.Products.Product;

import java.util.Map;

//Single Responsibility Principle
public class Shop {

    private Map<Product, Integer> products;

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public Product findProduct(int id) {
        return products.entrySet().stream().filter(x -> x.getKey().getId() == id)
                .map(Map.Entry::getKey)
                .findFirst().get();
    }

    public void removeProduct(Product product, int count) throws Exception {
        if (products.get(product) < count) {
            throw new Exception("Товара недостаточно!");
        } else {
            products.put(product, products.get(product) - count);
        }
    }

    public void addProduct(Product product, int count) {
        products.put(product, products.get(product) + count);
    }

    public Shop(Map<Product, Integer> products) {
        this.products = products;
    }
}
