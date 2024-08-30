package Products;

public class Beverage extends Product {

    private double volume;

    public Beverage(int id, String name, String manufacturer, int price, double volume) {
        super(id, name, manufacturer, price);
        this.volume = volume;
        this.addKeyWords("Beverage");
    }
}
