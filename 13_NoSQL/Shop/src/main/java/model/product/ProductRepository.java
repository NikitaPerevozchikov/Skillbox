package model.product;

import com.mongodb.client.MongoCollection;
import model.BaseRepository;
import org.bson.Document;

public class ProductRepository {
    private BaseRepository mongoDB;
    private MongoCollection<Document> products;
    private String nameCollection;

    public ProductRepository(BaseRepository mongoDB) {
        this.mongoDB = mongoDB;
        nameCollection = "products";
        products = mongoDB.addCollectionInBase(nameCollection);
    }

    public void addProduct(Product product) {
        if (!mongoDB.isObjectInCollectionExist(nameCollection, product.getName())) {
            mongoDB.addObjectInCollection(products, product);
        }
    }

    public long getCountShop() {
        return products.countDocuments();
    }
}
