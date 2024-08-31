package Notification;

public class EmailNotification implements NotificationService {
    @Override
    public void sendMessage(String message) {
        System.out.println("Подтверждение заказа № " + message + " отправлено на e-mail!");
    }
}