package employe;

public abstract class Worker implements Employee {
    protected double salary; //оклад
    protected double wages; //зарплата
    protected String position; //должность
    protected Company company; //компания

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }

    public double getWages() {
        return wages;
    }

    @Override
    public abstract double getMonthSalary();

}
