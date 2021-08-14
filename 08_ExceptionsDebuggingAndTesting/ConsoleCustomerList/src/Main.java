import java.util.Scanner;

public class Main {
    private static String addCommand = "add Василий Петров " +
            "vasily.petrov@gmail.com +79215637722";
    private static String commandExamples = "\t" + addCommand + "\n" +
            "\tlist\n\tcount\n\tremove Василий Петров";
    private static String commandError = "Wrong command! Available command examples: \n" +
            commandExamples;
    private static String helpText = "Command examples:\n" + commandExamples;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerStorage executor = new CustomerStorage();
        for (; ; ) {
            try {
                String command = scanner.nextLine();
                String[] tokens = command.split("\\s+", 2);
                if (tokens[0].toLowerCase().equals("add")) {  //добавил toLowerCase()
                    executor.addCustomer(tokens[1]);
                } else if (tokens.length > 1) {
                    throw new Exception(commandError);  //Комманда некорректна
                } else if (tokens[0].toLowerCase().equals("list")) {  //добавил toLowerCase()
                    executor.listCustomers();
                } else if (tokens[0].toLowerCase().equals("remove")) {  //добавил toLowerCase()
                    executor.removeCustomer(tokens[1]);
                } else if (tokens[0].toLowerCase().equals("count")) {  //добавил toLowerCase()
                    System.out.println("There are " + executor.getCount() + " customers");
                } else if (tokens[0].toLowerCase().equals("help")) {  //добавил toLowerCase()
                    System.out.println(helpText);
                } else {
                    throw new Exception(commandError);  //Комманда некорректна
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
