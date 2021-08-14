package employe;

public class Manager extends Worker {
    protected double profitCompany; //сколько продал менеджер

    public Manager(double salary) {
        this.salary = salary;
        position = "Менеджер";
    }

    @Override
    public double getMonthSalary() {
        profitCompany = 1000000 * ((double) ((int) (Math.random() * 100))) / 100;
        return wages = salary + profitCompany * 0.05;
    }

}
