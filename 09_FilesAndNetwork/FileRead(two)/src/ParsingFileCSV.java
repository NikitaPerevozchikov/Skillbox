import java.util.ArrayList;
import java.util.List;

public class ParsingFileCSV {

    public static ArrayList<Operation> createOperationCSV(List<String> listOperations) {
        ArrayList<Operation> operations = new ArrayList<>();
        for (int i = 1; i < listOperations.size(); i++) {
            String[] fragment = listOperations.get(i).split("\\\"");
            if (fragment.length > 1) {
                fragment[1] = fragment[1].replace(',', '.');
                String newFragment = fragment[0] + fragment[1];
                listOperations.set(i, newFragment);
            }
            String[] transaction = listOperations.get(i).split("\\,");
            if (transaction.length == 8) {
                operations.add(new Operation(processingItemDefinitionOperation(transaction[5]),
                        Double.parseDouble(transaction[6]),
                        Double.parseDouble(transaction[7])));
            }
        }
        return operations;
    }

    private static String processingItemDefinitionOperation(String definitionsOperation) {
        String[] definition = definitionsOperation.split("[\\s]{3,}");
        if (definition.length == 1) {
            return definitionsOperation;
        }
        int indexOne = definition[1].lastIndexOf('\\');
        int indexTwo = definition[1].lastIndexOf('/');
        if (indexOne >= 0) {
            definition[1] = definition[1].substring(indexOne + 1);
        } else {
            definition[1] = definition[1].substring(indexTwo + 1);
        }
        return definition[1].trim();
    }
}
