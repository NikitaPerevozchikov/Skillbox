import java.util.ArrayList;
import java.util.List;

public class ParsingFileORG {

    public static ArrayList<Operation> createOperationORG(List<String> listOperations) {
        ArrayList<Operation> operations = new ArrayList<>();
        for (int i = 2; i < listOperations.size(); i++) {
            String stringOperation = listOperations.get(i).substring(1, listOperations.get(i).length() - 1).trim();
            String[] transaction = stringOperation.split("\\s+?\\|\\s+?");
            for (int j = 0; j < transaction.length; j++) {
                if (transaction[j].length() == 0) {
                    transaction[j] = "0";
                }
            }
            if (transaction.length == 5) {
                operations.add(new Operation(transaction[1],
                        Double.parseDouble(transaction[3].replace(",", ".")),
                        Double.parseDouble(transaction[4].replace(",", "."))));
            }
        }
        return operations;
    }


}
