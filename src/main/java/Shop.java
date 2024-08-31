import Products.Product;

import java.util.Map;
import java.util.UUID;

//Single Responsibility Principle
public class Shop {

    private Map<Product, Integer> products;

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public Product findProduct(UUID id) {
        return products.entrySet().stream().filter(x -> x.getKey().getId().equals(id))
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

    public void printCatalog() {
        System.out.println("Список товаров:");
        products.forEach((key, value) -> System.out.println(key + ", Количество = " + value));
    }
}
