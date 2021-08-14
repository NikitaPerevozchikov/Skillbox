import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Path path = Paths.get("out/movementList.csv");
        ArrayList<Operation> operations = Operation.addListAndPointDelete(path);
        Map<String, Double> itemExpenses = operations.stream()
                .filter(o -> o.outputMoney > 0)
                .collect(Collectors.groupingBy(Operation::getDefinitionsOperation, Collectors.summingDouble(Operation::getOutputMoney)));

        double expenses = operations.stream().mapToDouble(Operation::getOutputMoney).sum();
        double revenue = operations.stream().mapToDouble(Operation::getInputMoney).sum();

        System.out.println("Виды расходов");

        for (String key : itemExpenses.keySet()) {
            System.out.printf("Статья: \"%s\" сумма: %.2f руб.%n", key, itemExpenses.get(key));
        }

        System.out.printf("%nСумма расходов: %.2f руб.%n", expenses);
        System.out.printf("Сумма доходов: %.2f руб.", revenue);


    }
}
