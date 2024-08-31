package Products;

public class MineralWater extends Beverage {

    //Liskov Substitution Principle
    private boolean sparkling;

    public MineralWater(String name, String manufacturer, int price, double volume, boolean sparkling) {
        super(name, manufacturer, price, volume);
        this.sparkling = sparkling;
        this.addKeyWords("Mineral");
        if (sparkling) {
            this.addKeyWords("Sparkling");
        } else {
            this.addKeyWords("Still");
        }
    }
}
