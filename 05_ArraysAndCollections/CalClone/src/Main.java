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
                if (calendar.size() == 0) {
                    System.out.println("Список дел пуст");
                    continue;
                }
                if (comTrue[0].equals("LIST")) {
                    System.out.println("Список дел: ");
                    for (int i = 0; i < calendar.size(); i++) {
                        System.out.println(i + " - " + calendar.get(i));
                    }
                    continue;
                }
                System.out.println("Некорректный ввод");
                continue;
            }

            if (comTrue[1].matches("[0-9]+")) {
                index = Integer.parseInt(comTrue[1]);
            }

            if (comTrue[0].equals("ADD")) {
                String dodo = "";
                if (index >= 0 && index <= calendar.size() + 1) {

                    for (int i = 1; i < comTrue.length; i++) {
                        dodo += comTrue[i] + " ";
                    }
                    calendar.add(index, dodo);
                } else {
                    for (int i = 1; i < comTrue.length; i++) {
                        dodo += comTrue[i] + " ";
                    }
                    calendar.add(dodo);
                }
                System.out.println("Задание добавлено в список дел");
            }

            if (comTrue[0].equals("EDIT")) {
                String dodo = "";
                if (index >= 0 && index < calendar.size()) {

                    for (int i = 2; i < comTrue.length; i++) {
                        dodo += comTrue[i] + " ";
                    }
                    calendar.set(index, dodo);
                    System.out.println("Задание заменено в списке дел");
                } else {
                    System.out.println("Неккоректный ввод");
                }
            }

            if (comTrue[0].equals("DELETE")) {
                if (comTrue.length == 2 && index >= 0 && index <= calendar.size() + 1) {
                    calendar.remove(index);
                    System.out.println("Задание удалено из списка дел");
                } else {
                    System.out.println("Неккоректный ввод");
                }
            }
        }
    }
}
