package Products;

public class Beverage extends Product {

    private double volume;

    public Beverage(String name, String manufacturer, int price, double volume) {
        super(name, manufacturer, price);
        this.volume = volume;
        this.addKeyWords("Beverage");
    }
}
