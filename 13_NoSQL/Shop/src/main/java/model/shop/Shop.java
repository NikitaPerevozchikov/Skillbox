package model.shop;

import interfaces.Essence;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Essence {

    private String name;
    private List<String> products;

    public Shop(String name) {
        this.name = name;
        products = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    @Override
    public Document toDocument() {
        Document document = new Document();
        document.put("name", name);
        document.put("products", products);
        return document;
    }
}
