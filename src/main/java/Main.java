import Products.Eggs;
import Products.MineralWater;
import Products.Product;

import java.util.*;
import java.awt.*;

public class Main {

    private static Shop shop;
    private static Customer customer = new Customer("Ivan");

    public static void main(String[] args) {
        Product bonAqua = new MineralWater(1, "BonAqua", "XXX1", 5, 0.5, false);
        Product narzan = new MineralWater(2, "Нарзан", "XXX2", 6, 1, true);
        Product whiteEggs = new Eggs(3, "Синявские", "XXX3", 10, Color.WHITE, 10);

        Map<Product, Integer> products = new HashMap<>();
        products.put(bonAqua, 6);
        products.put(narzan, 3);
        products.put(whiteEggs, 2);

        shop = new Shop(products);
        System.out.println("Добро пожаловать!");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите номер действия:\n" +
                    "1. Сделать заказ\n" +
                    "2. Вернуть заказ\n" +
                    "3. Покажи мои заказы\n" +
                    "4. Повторить заказ\n" +
                    "5. Покажи товары\n" +
                    "0. Выход\n");
            int number = Integer.parseInt(scanner.nextLine());
            if (number == 0) break;

            switch (number) {
                case (1):
                    makeOrder(scanner);
                    break;
                case (2):
                    returnOrRepeatOrder(scanner, true);
                    break;
                case (3):
                    printOrders();
                    break;
                case (4):
                    returnOrRepeatOrder(scanner, false);
                    break;
                case (5):
                    System.out.println("В магазине в наличии:");
                    shop.getProducts().forEach((key, value) -> System.out.println(key + ", количество: " + value));
                    break;
                default:
                    break;
            }
        }
        scanner.close();
    }

    private static void returnOrRepeatOrder(Scanner scanner, boolean isReturn) {
        System.out.println("Введите id заказа:");
        if (!printOrders()) return;
        String id = scanner.nextLine();
        try {
            Order oldOrder = customer.findOrder(UUID.fromString(id));
            for (Map.Entry<Product, Integer> product : oldOrder.getCart().entrySet()) {
                if (isReturn)
                {
                    shop.addProduct(product.getKey(), product.getValue());
                }
                else
                {
                    shop.removeProduct(product.getKey(), product.getValue());
                    Order order = new Order(oldOrder.getCart(), oldOrder.getAddress());
                    customer.addOrder(order);
                    System.out.println("Заказ готов!");
                }
            }
        }
        catch (Exception exception)
        {
            System.out.println(exception.getMessage());
        }
    }

    private static void makeOrder(Scanner scanner) {
        Map<Product, Integer> cart = new HashMap<>();
        while (true) {
            System.out.println("Добавьте товар в корзину, введя его номер:\n" +
                    "1. BonAqua\n" +
                    "2. Нарзан\n" +
                    "3. Яйца белые\n" +
                    "0. Завершить сбор корзины\n");
            int id = Integer.parseInt(scanner.nextLine());
            if (id == 0) break;
            try {
                Product product = shop.findProduct(id);
                System.out.println("Введите количество:");
                int count = Integer.parseInt(scanner.nextLine());
                shop.removeProduct(product, count);
                cart.put(product, count);
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
        if (!cart.isEmpty()) {
            System.out.println("Введите адрес доставки:");
            String address = scanner.nextLine();
            Order order = new Order(cart, address);
            customer.addOrder(order);
            System.out.println("Заказ готов!");
        } else {
            System.out.println("Заказ не был собран, корзина пустая");
        }
    }

    private static boolean printOrders() {
        System.out.println("Мои заказы:");
        if (!customer.getOrders().isEmpty())
        {
            customer.getOrders().forEach(System.out::println);
            return true;
        }
        else
        {
            System.out.println("Заказов нет");
            return false;
        }
    }
}