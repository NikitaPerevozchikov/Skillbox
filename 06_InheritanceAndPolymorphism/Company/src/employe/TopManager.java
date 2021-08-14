package employe;

public class TopManager extends Worker {
    public TopManager(double salary) {
        this.salary = salary;
        position = "Топменеджер";
    }

    @Override
    public double getMonthSalary() {
        if (company.getIncome() >= 10000000) {
            return wages = salary * 2.5;
        }
        return wages = salary;
    }
}
