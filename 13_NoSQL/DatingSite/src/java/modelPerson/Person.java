package modelPerson;

import java.util.Random;

public class Person {
    private int id;
    private long dateRegistration;
    private String name;
    private String surname;
    private String gender;

    public Person(int id) {
        this.id = id;
        dateRegistration = System.currentTimeMillis();
        gender = generationGender();
        name = generationName(gender);
        surname = generationSurname();
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getDateRegistration() {
        return dateRegistration;
    }

    public void setDateRegistration(long dateRegistration) {
        this.dateRegistration = dateRegistration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return id + "";
    }

    public int getId() {
        return id;
    }

    private String generationGender() {
        int pick = new Random().nextInt(GenderPerson.values().length);
        return GenderPerson.values()[pick].toString();
    }

    private String generationName(String gender) {
        if (gender.equals("MEN")) {
            int pick = new Random().nextInt(MenName.values().length);
            return MenName.values()[pick].toString();
        }
        int pick = new Random().nextInt(WomenName.values().length);
        return WomenName.values()[pick].toString();
    }

    private String generationSurname() {
        int pick = new Random().nextInt(Surname.values().length);
        return Surname.values()[pick].toString();
    }
}
