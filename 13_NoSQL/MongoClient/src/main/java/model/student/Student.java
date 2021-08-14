package model.student;

import model.Interfaces.Essence;
import org.bson.Document;

import java.util.Arrays;

public class Student implements Essence {
    private String name;
    private int age;
    private String[] courses;

    public Student(String name, int age, String[] courses) {
        super();
        this.name = name;
        this.age = age;
        this.courses = courses;
    }

    @Override
    public Document toDocument() {
        Document document = new Document();
        document.put("name", name);
        document.put("age", age);
        document.put("courses", Arrays.toString(courses));
        return document;
    }


}
