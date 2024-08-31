package Products;

import java.awt.*;

public class Eggs extends Product {

    private Color color;
    private int countInPack;

    public Eggs(String name, String manufacturer, int price, Color color, int countInPack) {
        super(name, manufacturer, price);
        this.color = color;
        this.countInPack = countInPack;
        this.addKeyWords("Eggs");
    }
}


