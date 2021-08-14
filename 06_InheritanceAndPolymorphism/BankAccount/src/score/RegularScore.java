package score;

public class RegularScore {

    protected double sumScore;

    public RegularScore(double sumScore) {
        this.sumScore = sumScore;
    }

    public double getSumScore() {
        return sumScore;
    }

    public double addMoney(double sum) {
        return sumScore += sum;
    }

    public double takeMoney(double sum) {
        if (sum >= sumScore) {
            System.out.println("Недостаточно средств");
            return sumScore;
        }
        return sumScore -= sum;
    }

}
