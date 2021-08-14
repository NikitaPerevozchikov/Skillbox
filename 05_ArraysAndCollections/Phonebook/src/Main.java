import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        HashMap<String, String> bookName = new HashMap<>();
        HashMap<String, String> bookNumber = new HashMap<>();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите имя или номер телефона: ");
            String text = scanner.nextLine();

            if (text.equals("LIST")) {
                printMap(bookName);
                continue;
            }

            if (text.matches("^[0-9]+")) {
                if (bookName.containsKey(text)) {
                    System.out.println("Имя: " + bookName.get(text) + ". Номер телефона: " + text);
                    continue;
                }

                boolean i = false;
                while (i == false) {
                    System.out.println("Введите имя: ");
                    String name = scanner.nextLine();

                    if (bookNumber.containsKey(name)) {
                        System.out.println("Такое имя уже существует");
                        continue;
                    }
                    bookNumber.put(name, text);
                    bookName.put(text, name);
                    i = true;
                }
                continue;
            }

            if (bookNumber.containsKey(text)) {
                System.out.println("Имя: " + text + ". Номер телефона: " + bookNumber.get(text));
                continue;
            }

            boolean i = false;
            while (i == false) {
                System.out.println("Введите номер: ");
                String number = scanner.nextLine();
                if (number.matches("^[0-9]+")) {
                    if (bookName.containsKey(number)) {
                        System.out.println("Такой номер уже существует");
                        continue;
                    }
                    bookNumber.put(text, number);
                    bookName.put(number, text);
                    i = true;
                    continue;
                }
                System.out.println("Номер введён некорректно.");
            }
        }
    }

    private static void printMap(Map<String, String> map) {
        for (String key : map.keySet()) {
            System.out.println("Имя: " + map.get(key) + ". Номер телефона: " + key);
        }
    }
}

