package clients;

public class LegalEntitly extends Client {

    public LegalEntitly(double sum) {
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
        if (sum * 1.01 > this.sum) {
            System.out.println("Недостаточно средств для выполнения оперции");
            return;
        }
        this.sum -= sum * 1.01;
    }

}
