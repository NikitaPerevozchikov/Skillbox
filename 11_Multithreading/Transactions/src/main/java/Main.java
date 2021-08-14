import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<String, Account> accounts = new HashMap<>();
        Bank bank = new Bank(accounts);
        long countInBank;

        accounts.put("1", new Account("1"));
        accounts.put("2", new Account("2"));
        countInBank = bank.getBalanceInBank(accounts);

        Runnable taskOne = () -> {
            bank.transfer("1", "2", 100, countInBank);
        };
        Runnable taskTwo = () -> {
            System.out.println(bank.getBalance("2"));
            System.out.println(bank.getBalance("2"));
        };
        new Thread(taskTwo).start();
        new Thread(taskOne).start();

    }
}
