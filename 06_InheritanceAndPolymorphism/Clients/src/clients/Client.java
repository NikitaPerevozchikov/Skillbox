package clients;

public abstract class Client {

    protected double sum;

    public abstract double getSum();

    public abstract void addMoney(double sum);

    public abstract void takeMoney(double sum);

}
