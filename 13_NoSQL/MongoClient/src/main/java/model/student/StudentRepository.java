package model.student;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import model.BaseRepository;
import org.bson.Document;

import java.util.Objects;


public class StudentRepository {
    private BaseRepository mongoDB;
    private MongoCollection<Document> collection;

    public StudentRepository(BaseRepository mongoDB) {
        this.mongoDB = mongoDB;
        collection = addCollection();
    }

    private MongoCollection<Document> addCollection() {
        return mongoDB.addCollectionInBase("students");
    }

    public void addStudent(Student student) {
        mongoDB.addObjectInCollection(collection, student);
    }

    public long getCountStudents () {
        return collection.countDocuments();
    }

    public long getCountStudentsAge40 () {
        return collection.countDocuments(Filters.gt("age", 40));
    }

    public String getNameYoungStudent() {
        return Objects.requireNonNull(collection.find()
                .sort(Sorts.ascending("age", "name"))
                .first()).getString("name");
    }

    public String getCoursesOldStudent() {
        return Objects.requireNonNull(collection.find()
                .sort(Sorts.orderBy(Sorts.descending("age"), Sorts.ascending("name")))
                .first()).getString("courses");
    }

}

