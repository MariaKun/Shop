package Products;

import java.util.ArrayList;
import java.util.List;

//Dependency inversion principle - в программе работаем не с конретными продуктами, а с абстракцией
//Open-closed principle
public abstract class Product {
    private int price;
    private int rate;
    private String manufacturer;
    private String name;
    private int id;
    private List<String> keyWords = new ArrayList<>();

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public int getRate() {
        return rate;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public void addKeyWords(String keyWord) {
        this.keyWords.add(keyWord);
    }

    public Product(int id, String name, String manufacturer, int price) {
        this.id = id;
        this.price = price;
        this.manufacturer = manufacturer;
        this.name = name;
    }

    @Override
    public String toString() {
        return
                "price=" + price +
                        ", rate=" + rate +
                        ", keyWords=" + keyWords +
                        ", manufacturer='" + manufacturer + '\'' +
                        ", name='" + name + '\'';
    }
}
