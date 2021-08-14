import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Path path = Paths.get("out/movementList.csv");
        Path pathPlus = Paths.get("out/movenent.org");

        ArrayList<Operation> operations = whichExpansionForRead(path);

        double expenses = operations.stream().mapToDouble(Operation::getOutputMoney).sum();
        double revenue = operations.stream().mapToDouble(Operation::getInputMoney).sum();
        System.out.printf("%nСумма расходов: %.2f руб.%n", expenses);
        System.out.printf("Сумма доходов: %.2f руб.%n", revenue);

        System.out.println("Виды расходов:");
        operations.stream()
                .collect(Collectors.groupingBy(Operation::getDefinitionsOperation,
                        Collectors.mapping(Operation.Summary::fromTransaction,
                                Collectors.reducing(Operation.Summary::merge))))
                .forEach((s, sum) -> System.out.println("Статья: \"" + s +
                        "\"\tСумма доходов: " + sum.get().income + " руб. " +
                        "\tСумма расходов: " + sum.get().withdraw + " руб."));
    }

    private static ArrayList<Operation> whichExpansionForRead(Path path) {
        List<String> listOperations = null;
        try {
            listOperations = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (listOperations != null) {
            if (path.toString().endsWith(".csv")) {
                return ParsingFileCSV.createOperationCSV(listOperations);
            }
            if (path.toString().endsWith(".org")) {
                return ParsingFileORG.createOperationORG(listOperations);
            }
        }
        return new ArrayList<>();
    }
}
