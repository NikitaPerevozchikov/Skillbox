package model;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import model.Interfaces.Essence;
import org.bson.Document;

import java.util.ArrayList;

public class BaseRepository {

    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;

    public BaseRepository(String host, int port, String nameBase) {
        mongoClient = new MongoClient(host, port);
        mongoDatabase = mongoClient.getDatabase(nameBase);
    }

    public MongoCollection<Document> addCollectionInBase(String nameCollection) {
        if (!collectionExists(nameCollection)) {
            mongoDatabase.createCollection(nameCollection);
        }
        mongoDatabase.getCollection(nameCollection).drop();
        return mongoDatabase.getCollection(nameCollection);
    }

    public void addObjectInCollection(MongoCollection<Document> mongoCollection, Essence essence) {
        mongoCollection.insertOne(essence.toDocument());
    }

    private boolean collectionExists(String collectionName) {
        return mongoDatabase.listCollectionNames().into(new ArrayList<>()).contains(collectionName);
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public MongoDatabase getMongoDatabase() {
        return mongoDatabase;
    }
}
