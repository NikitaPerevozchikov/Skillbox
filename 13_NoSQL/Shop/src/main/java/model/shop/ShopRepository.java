package model.shop;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import model.BaseRepository;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Objects;

public class ShopRepository {
    private BaseRepository mongoDB;
    private MongoCollection<Document> shops;
    private String nameCollection;

    public ShopRepository(BaseRepository mongoDB) {
        this.mongoDB = mongoDB;
        nameCollection = "shops";
        shops = mongoDB.addCollectionInBase(nameCollection);
    }

    public void addShop(Shop shop) {
        if (!mongoDB.isObjectInCollectionExist(nameCollection, shop.getName())) {
            mongoDB.addObjectInCollection(shops, shop);
        }
    }

    public long getCountShop() {
        return shops.countDocuments();
    }

    public String getAllProducts(String nameShop) {
        return Objects.requireNonNull(shops.find().filter(Filters.eq("name", nameShop)).first()).get("products").toString();
    }

    public boolean putProduct(String nameShop, String nameProduct) {
        if (mongoDB.isObjectInCollectionExist(nameCollection, nameShop)
                && mongoDB.isObjectInCollectionExist("products", nameProduct)) {
            shops.findOneAndUpdate(Filters.eq("name", nameShop), Updates.addToSet("products", nameProduct));
            return true;
        }
        return false;
    }
}
