package score;

import java.util.Calendar;

public class DepositoryScore extends RegularScore {
    private Calendar dateAdd;

    public DepositoryScore(int sumScore) {
        super(sumScore);
        dateAdd = Calendar.getInstance();
    }

    public Calendar getDateAdd() {
        return dateAdd;
    }

    public double takeMoney(double sum) {
        Calendar dateReal = Calendar.getInstance();
        dateReal.add(Calendar.MONTH, -1);
        if (dateReal.before(dateAdd)) {
            System.out.println("Снятие средств невозможно.");
            return sumScore;
        }
        if (sum >= sumScore) {
            System.out.println("Недостаточно средств");
            return sumScore;
        }
        return sumScore -= sum;
    }

    public double addMoney(double sum) {
        dateAdd = Calendar.getInstance();
        return sumScore += sum;
    }


}
