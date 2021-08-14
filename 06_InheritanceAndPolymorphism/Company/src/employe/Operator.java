package employe;

public class Operator extends Worker {
    public Operator(double salary) {
        this.wages = salary;
        position = "Оператор";

    }
    @Override
    public double getMonthSalary() {
        return wages;
    }


}
