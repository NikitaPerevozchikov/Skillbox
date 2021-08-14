package model;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Field;
import interfaces.Essence;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;

public class BaseRepository {
    private MongoClient mongoClient;
    private MongoDatabase mongoDB;

    public BaseRepository(String host, int port, String nameBase) {
        mongoClient = new MongoClient(host, port);
        mongoDB = mongoClient.getDatabase(nameBase);
    }

    public MongoCollection<Document> addCollectionInBase(String nameCollection) {
        if (!isCollectionExist(nameCollection)) {
            mongoDB.createCollection(nameCollection);
        }
        mongoDB.getCollection(nameCollection).drop();
        return mongoDB.getCollection(nameCollection);
    }

    public void addObjectInCollection(MongoCollection<Document> mongoCollection, Essence essence) {
        mongoCollection.insertOne(essence.toDocument());
    }

    private boolean isCollectionExist(String collectionName) {
        return mongoDB.listCollectionNames().into(new ArrayList<>()).contains(collectionName);
    }

    public boolean isObjectInCollectionExist(String nameCollection, String nameEssence) {
        return mongoDB.getCollection(nameCollection)
                .distinct("name", String.class).into(new ArrayList<>())
                .contains(nameEssence);
    }

    public void statisticProducts() {
        mongoDB.getCollection("shops")
                .aggregate(
                        Arrays.asList(
                                Aggregates.lookup("products", "products", "name", "list_product"),
                                Aggregates.unwind("$list_product"),
                                Aggregates.addFields(new Field("priceLess100",
                                                new Document("$cond",
                                                        new Document("if",
                                                                new Document("$lt", Arrays.asList("$list_product.price", 100)
                                                                )
                                                        )
                                                                .append("then", 1)
                                                                .append("else", 0)
                                                )
                                        )
                                ),
                                Aggregates.group("$name",
                                        Accumulators.sum("count", 1),
                                        Accumulators.avg("agv", "$list_product.price"),
                                        Accumulators.max("max", "$list_product.price"),
                                        Accumulators.min("min", "$list_product.price"),
                                        Accumulators.push("push", "$list_product.price"),
                                        Accumulators.sum("less100", "$priceLess100")
                                )
                        )
                )
                .into(new ArrayList<>())
                .forEach(e -> {
                            System.out.format("Магазин: %s\n", e.get("_id"));
                            System.out.format("\tОбщее количество товаров: %s\n", e.get("count"));
                            System.out.format("\tСредняя стоимость товаров: %s\n", e.get("agv"));
                            System.out.format("\tМаксимальная стоимость товара: %s\n", e.get("max"));
                            System.out.format("\tМинимальная стоимость товара: %s\n", e.get("min"));
                            System.out.format("\tКоличество товаров стоимостью менее 100: %s\n", e.get("less100"));
                        }
                );
    }
}
