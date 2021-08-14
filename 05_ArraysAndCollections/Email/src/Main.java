import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HashSet<String> emails = new HashSet<>();
        Scanner scanner = new Scanner(System.in);
        String regEmail = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";

        while (true) {
            System.out.print("Введите комманду: ");
            String setcommand = scanner.nextLine();
            String command = setcommand.trim();
            String[] commandWolds = command.split("\\s+");

            if (commandWolds.length > 2) {
                danger();
            }

            if (commandWolds.length == 1) {
                if (!(commandWolds[0].equals("LIST")) || emails.size() == 0) {
                    danger();
                } else {
                    for (String email : emails) {
                        System.out.println(email);
                    }
                }
            }

            if (commandWolds.length == 2) {
                if (!(commandWolds[0].equals("ADD")) || !(commandWolds[1].matches(regEmail)) || emails.contains(commandWolds[1])) {
                    danger();
                } else {
                    emails.add(commandWolds[1]);
                    System.out.println("Email адресс добавлен в список");
                }
            }
        }
    }

    public static void danger() {
        System.out.println("Некорректный ввод");
    }

}
