import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int index = -1;
        ArrayList<String> calendar = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Введите комманду:");
            String setcommand = scanner.nextLine();
            String command = setcommand.trim();
            String[] comTrue = command.split("\\s+");

            if (comTrue.length == 1) {
                if (calendar.size() == 0 && comTrue[0].equals("LIST")) {
                    System.out.println("Список дел пуст");
                    continue;
                }
                if (comTrue[0].equals("LIST")) {
                    System.out.println("Список дел: ");
                    arrayGet(calendar);
                    continue;
                }
                System.out.println("Некорректный ввод");
                continue;
            }

            if (comTrue[1].matches("[0-9]+")) {
                index = Integer.parseInt(comTrue[1]);
            }

            switch (comTrue[0]) {
                case "ADD": {
                    String dodo = "";
                    if (index >= 0 && index <= calendar.size() + 1) {
                        calendar.add(index, calendarSet(comTrue, 2));
                    } else {
                        calendar.add(calendarSet(comTrue, 1));
                    }
                    System.out.println("Задание добавлено в список дел");
                    break;
                }
                case "EDIT": {
                    if (index >= 0 && index < calendar.size() && comTrue.length >= 3) {
                        calendar.set(index, calendarSet(comTrue, 2));
                        System.out.println("Задание заменено в списке дел");
                    } else {
                        System.out.println("Неккоректный ввод");
                    }
                    break;
                }
                case "DELETE": {
                    if (comTrue.length == 2 && index >= 0 && index <= calendar.size() + 1) {
                        calendar.remove(index);
                        System.out.println("Задание удалено из списка дел");
                    } else {
                        System.out.println("Неккоректный ввод");
                    }
                    break;
                }
            }
        }
    }

    private static void arrayGet(ArrayList<String> type) {
        for (int i = 0; i < type.size(); i++) {
            System.out.println(i + " - " + type.get(i));
        }
    }

    private static String calendarSet(String[] type, int index) {
        String text = "";
        for (int i = index; i < type.length; i++) {
            text += type[i] + " ";
        }
        return text;
    }
}



