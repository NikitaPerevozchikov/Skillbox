package score;

public class CardScore extends RegularScore {
    public CardScore(int sumScore) {
        super(sumScore);
    }

    public double takeMoney(double sum) {
        if (sum * 1.1 >= sumScore) {
            System.out.println("Недостаточно средств");
            return sumScore;
        }
        return sumScore -= sum * 1.01;
    }
}
