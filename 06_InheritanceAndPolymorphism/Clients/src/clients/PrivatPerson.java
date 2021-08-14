package clients;

public class PrivatPerson extends Client {

    public PrivatPerson(double sum) {
        this.sum = sum;
    }

    @Override
    public double getSum() {
        return sum;
    }

    @Override
    public void addMoney(double sum) {
        this.sum += sum;
    }

    @Override
    public void takeMoney(double sum) {
        if (sum > this.sum) {
            System.out.println("Недостаточно средств для выполнения оперции");
            return;
        }
        this.sum -= sum;
    }

}
