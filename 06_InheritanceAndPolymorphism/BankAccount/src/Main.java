import score.CardScore;
import score.DepositoryScore;
import score.RegularScore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Main {
    public static void main (String[] args) {
        DateFormat dateFormat = new SimpleDateFormat("dd.mm.YYYY (HH:mm:ss)");

        RegularScore Score1 = new RegularScore(100);
        System.out.println("Сумма на вашем счёте: " + Score1.getSumScore() + " руб.");
        Score1.addMoney(1500);
        System.out.println("Сумма на вашем счёте: " + Score1.getSumScore() + " руб.");
        Score1.takeMoney(5000);
        System.out.println("Сумма на вашем счёте: " + Score1.getSumScore() + " руб.");

        DepositoryScore Score2 = new DepositoryScore(1500);
        System.out.println("Сумма на вашем счёте: " + Score2.getSumScore() + " руб.");
        Score2.addMoney(1500);
        System.out.println("Сумма на вашем счёте: " + Score2.getSumScore() + " руб.");
        Score2.takeMoney(10000);
        System.out.println("Сумма на вашем счёте: " + Score2.getSumScore() + " руб.");
        System.out.println("Дата последнего внесения средств: " + dateFormat.format(Score2.getDateAdd().getTime()));

        CardScore Score3 = new CardScore(100);
        System.out.println("Сумма на вашем счёте: " + Score3.getSumScore() + " руб.");
        Score3.addMoney(1500);
        System.out.println("Сумма на вашем счёте: " + Score3.getSumScore() + " руб.");
        Score3.takeMoney(500);
        System.out.println("Сумма на вашем счёте: " + Score3.getSumScore() + " руб.");

    }
}
