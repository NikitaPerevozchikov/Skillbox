import java.util.HashMap;

public class CustomerStorage {
    private HashMap<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) throws Exception {

        String[] components = data.split("\\s+");

        //проверка на количество элементов массива
        if (components.length != 4) {
            throw new IllegalArgumentException("Wrong format. Right format: add Василий Петров vasily.petrov@gmail.com +79215637722");
        }

        //В имени и фамилии должны быть только буквы
        if (!components[0].chars().allMatch(Character::isLetter) || !components[1].chars().allMatch(Character::isLetter)) {
            throw new Exception("Wrong format. Name and first name must have only letters");
        }

        //E-mail должен соответствовать формату
        if (!components[2].matches("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$")) {
            throw new Exception("Wrong format. Email is incorrect format");
        }

        //Номер телефона должен соответствовать формату
        if (!components[3].split("")[0].equals("+") || !components[3].substring(1).matches("[0-9]+")) {
            throw new Exception("Wrong format. Phone number is incorrect format");
        }

        String name = components[0] + " " + components[1];

        storage.put(name, new Customer(name, components[3], components[2]));
    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        storage.remove(name);
    }

    public int getCount() {
        return storage.size();
    }
}