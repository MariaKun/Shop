package Notification;

public class MobileNotification implements NotificationService {
    @Override
    public void sendMessage(String message) {
        System.out.println("Подтверждение заказа № " + message + " отправлено в sms!");
    }
}