public class Operation {
    private String definitionsOperation;
    private double inputMoney;
    private double outputMoney;

    public Operation(String definitionsOperation, double inputMoney, double outputMoney) {
        this.definitionsOperation = definitionsOperation;
        this.inputMoney = inputMoney;
        this.outputMoney = outputMoney;
    }

    public String getDefinitionsOperation() {
        return definitionsOperation;
    }

    public Double getOutputMoney() {
        return outputMoney;
    }

    public double getInputMoney() {
        return inputMoney;
    }

    public static class Summary {
        double income;
        double withdraw;

        Summary(double income, double withdraw) {
            this.income = income;
            this.withdraw = withdraw;
        }

        static Summary merge(Summary s1, Summary s2) {
            return new Summary(s1.income + s2.income, s1.withdraw + s2.withdraw);
        }

        static Summary fromTransaction(Operation o) {
            return new Summary(o.inputMoney, o.outputMoney);
        }
    }
}