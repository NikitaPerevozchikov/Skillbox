import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Operation {
    String accountType;
    String accountNumber;
    String currency;
    Date dateOperation;
    String transactionReference;
    String definitionsOperation;
    double inputMoney;
    double outputMoney;

    public String getDefinitionsOperation() {
        return definitionsOperation;
    }

    public Double getOutputMoney() {
        return outputMoney;
    }

    public double getInputMoney() {
        return inputMoney;
    }

    public Operation(String accountType, String accountNumber, String currency, String dateOperation,
                     String transactionReference, String definitionsOperation, double inputMoney,
                     double outputMoney) {
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.currency = currency;
        try {
            this.dateOperation = new SimpleDateFormat("dd.MM.yy").parse(dateOperation);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.transactionReference = transactionReference;
        this.definitionsOperation = processingItemDefinitionOperation(definitionsOperation);
        this.inputMoney = inputMoney;
        this.outputMoney = outputMoney;
    }

    public static ArrayList<Operation> addListAndPointDelete(Path path) {
        List<String> listOperations = null;
        try {
            listOperations = Files.readAllLines(path);
            listOperations.remove(0);
            for (int i = 0; i < listOperations.size(); i++) {
                String[] fragment = listOperations.get(i).split("\\\"");
                if (fragment.length > 1) {
                    fragment[1] = fragment[1].replace(',', '.');
                    String newFragment = fragment[0] + fragment[1];
                    listOperations.set(i, newFragment);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Operation.fileRead(listOperations);
    }

    private static ArrayList<Operation> fileRead(List<String> listOperations) {
        ArrayList<Operation> operations = new ArrayList<>();
        for (String item : listOperations) {
            String[] fragment = item.split("\\,");
            operations.add(new Operation(
                    fragment[0],
                    fragment[1],
                    fragment[2],
                    fragment[3],
                    fragment[4],
                    fragment[5],
                    Double.parseDouble(fragment[6]),
                    Double.parseDouble(fragment[7]))
            );
        }
        return operations;
    }

    private String processingItemDefinitionOperation(String definitionsOperation) {
        String[] definition = definitionsOperation.split("[\\s]{3,}");
        int indexOne = definition[1].lastIndexOf('\\');
        int indexTwo = definition[1].lastIndexOf('/');
        if (indexOne >= 0) {
            definition[1] = definition[1].substring(indexOne + 1);
        } else {
            definition[1] = definition[1].substring(indexTwo + 1);
        }
        return definition[1];
    }
}
