package model.product;

import interfaces.Essence;
import org.bson.Document;

public class Product implements Essence {

    private String name;
    private int price;

    public Product(String name, int prise) {
        this.name = name;
        this.price = prise;
    }

    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }

    @Override
    public Document toDocument() {
        Document document = new Document();
        document.put("name", name);
        document.put("price", price);
        return document;
    }
}
