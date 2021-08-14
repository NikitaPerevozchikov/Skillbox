package clients;

public class IndividualEntrepreneur extends Client {

    public IndividualEntrepreneur(double sum) {
        this.sum = sum;
    }

    @Override
    public double getSum() {
        return sum;
    }

    @Override
    public void addMoney(double sum) {
        if (sum < 1000) {
            this.sum += sum * 0.99;
            return;
        }
        this.sum += sum * 0.995;
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
