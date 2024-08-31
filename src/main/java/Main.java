import Notification.EmailNotification;
import Notification.MobileNotification;
import Notification.NotificationService;
import Products.Eggs;
import Products.MineralWater;
import Products.Product;

import java.util.*;
import java.awt.*;

public class Main {

    private static Shop shop;
    private static Customer customer = new Customer("Ivan");
    private static String end = "end";
    //Magics
    //Представили, что завезти на склад можно не менее minCount и не более maxCount единиц каждого наименования товара
    private static final int maxCount = 10;
    private static final int minCount = 1;

    public static void main(String[] args) {
        Random random = new Random();
        Product bonAqua = new MineralWater("BonAqua", "XXX1", 5, 0.5, false);
        Product narzan = new MineralWater("Нарзан", "XXX2", 6, 1, true);
        Product whiteEggs = new Eggs("Синявские", "XXX3", 10, Color.WHITE, 10);

        //Заполняем магазин товарами
        Map<Product, Integer> products = new HashMap<>();
        products.put(bonAqua, random.nextInt(minCount, maxCount));
        products.put(narzan, random.nextInt(minCount, maxCount));
        products.put(whiteEggs, random.nextInt(minCount, maxCount));
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
                    end + ". Выход\n");
            String number = scanner.nextLine();
            if (number.equals(end)) break;

            switch (Integer.parseInt(number)) {
                case (1):
                    makeOrder(scanner);
                    break;
                case (2):
                    returnOrRepeatOrder(scanner, true);
                    break;
                case (3):
                    customer.printOrders();
                    break;
                case (4):
                    returnOrRepeatOrder(scanner, false);
                    break;
                case (5):
                    shop.printCatalog();
                    break;
                default:
                    break;
            }
        }
        scanner.close();
    }

    //Don’t Repeat Yourself
    //2 метода со схожей логикой схлопнуты в 1, чтобы не плодить дубли кода
    private static void returnOrRepeatOrder(Scanner scanner, boolean isReturn) {
        System.out.println("Введите id заказа:");
        if (!customer.printOrders()) return;
        String id = scanner.nextLine();
        try {
            Order oldOrder = customer.findOrder(UUID.fromString(id));
            for (Map.Entry<Product, Integer> product : oldOrder.getCart().entrySet()) {
                if (isReturn) {
                    shop.addProduct(product.getKey(), product.getValue());
                } else {
                    shop.removeProduct(product.getKey(), product.getValue());
                    Order order = new Order(oldOrder.getCart(), oldOrder.getAddress());
                    customer.addOrder(order);
                    System.out.println("Заказ готов!");
                }
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private static void makeOrder(Scanner scanner) {
        Map<Product, Integer> cart = new HashMap<>();
        while (true) {
            System.out.println("Добавьте товар в корзину, введя его id:");
            shop.printCatalog();
            System.out.println(end + ". Отправить заказ");
            String id = scanner.nextLine();
            if (id.equals("end")) break;
            try {
                Product product = shop.findProduct(UUID.fromString(id));
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
            System.out.println("Заказ собран!");

            System.out.println("Отправить подтверждение заказа по" +
                    "\n1. email" +
                    "\n2. sms");
            String num = scanner.nextLine();
            switch (Integer.parseInt(num)) {
                case (1):
                    NotificationService emailNotification = new EmailNotification();
                    emailNotification.sendMessage(order.getId().toString());
                    break;
                case (2):
                    NotificationService mobileNotification = new MobileNotification();
                    mobileNotification.sendMessage(order.getId().toString());
                    break;
                default:
                    break;
            }
        } else {
            System.out.println("Заказ не был собран, корзина пустая");
        }
    }
}