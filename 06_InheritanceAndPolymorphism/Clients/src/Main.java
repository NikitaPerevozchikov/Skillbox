import clients.Client;
import clients.IndividualEntrepreneur;
import clients.LegalEntitly;
import clients.PrivatPerson;

public class Main {
    public static void main(String[] args) {

        Client Score1 = new PrivatPerson(1500);
        System.out.println(Score1.getSum());
        Score1.addMoney(500);
        System.out.println(Score1.getSum());
        Score1.takeMoney(500);
        System.out.println(Score1.getSum());


        Client Score2 = new LegalEntitly(1500);
        System.out.println(Score2.getSum());
        Score2.addMoney(500);
        System.out.println(Score2.getSum());
        Score2.takeMoney(500);
        System.out.println(Score2.getSum());


        Client Score3 = new IndividualEntrepreneur(1500);
        System.out.println(Score3.getSum());
        Score3.addMoney(500);
        System.out.println(Score3.getSum());
        Score3.takeMoney(500);
        System.out.println(Score3.getSum());
    }
}
