import org.apache.logging.log4j.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Bank {
    private HashMap<String, Account> accounts;
    private final Random random = new Random();
    private static final Logger logger = LogManager.getLogger("logger");
    private static final Marker inputFalseAccount = MarkerManager.getMarker("inputFalseAccount");
    private static final Marker lockingAccount = MarkerManager.getMarker("lockingAccount");
    private static final Marker transaction = MarkerManager.getMarker("transaction");

    public Bank(HashMap<String, Account> accounts) {
        this.accounts = accounts;
    }

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount) throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами.
     * Если сумма транзакции > 50000, то после совершения транзакции,
     * она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка
     * счетов (как – на ваше усмотрение)
     */
    public void transfer(String fromAccountNum, String toAccountNum, long amount, long countInBank) {

        if (!isAccountExist(fromAccountNum)) {
            return;
        }
        if (!isAccountExist(toAccountNum)) {
            return;
        }

        if (!fromAccountNum.equals(toAccountNum)) {
            if (amount > 50000) {
                try {
                    if (isFraud(fromAccountNum, toAccountNum, amount)) {
                        accounts.get(fromAccountNum).setLocked(true);
                        accounts.get(toAccountNum).setLocked(true);
                        System.out.format("Операция не выполнена. Счёта №%s и №%s заблокированы%n", fromAccountNum, toAccountNum);
                        logger.printf(Level.INFO, lockingAccount, "Аккаунт: " + fromAccountNum + " заблокирован");
                        logger.printf(Level.INFO, lockingAccount, "Аккаунт: " + toAccountNum + " заблокирован");
                        return;
                    }
                } catch (InterruptedException e) {
                    logger.printf(Level.ERROR, e.getMessage());
                }
            }

            synchronized (sortAccountForNumber(fromAccountNum, toAccountNum)) {
                synchronized (sortAccountForNumber(fromAccountNum, toAccountNum)) {
                    if (isPossibleTransfer(fromAccountNum, toAccountNum, amount)) {
                        accounts.get(fromAccountNum).subtractMoney(amount);
                        accounts.get(toAccountNum).addMoney(amount);
                        logger.printf(Level.INFO, transaction, fromAccountNum + " -> " + toAccountNum + " : " + amount);
                        System.out.format("Операция выполнена. Счёт №%s перевел счёту №%s сумму %d руб.%n", fromAccountNum, toAccountNum, amount);
                    }
                }
            }
        }
    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum) {
        return accounts.get(accountNum).getMoney();
    }

    public long getBalanceInBank(HashMap<String, Account> accounts) {
        List<Account> values = new ArrayList<>(accounts.values());
        return values.stream().mapToLong(Account::getMoney).sum();
    }

    private boolean isPossibleTransfer(String fromAccountNum, String toAccountNum, long amount) {
        return (!accounts.get(fromAccountNum).isLocked() ||
                !accounts.get(toAccountNum).isLocked()) &&
                accounts.get(fromAccountNum).getMoney() >= amount;
    }

    private Account sortAccountForNumber(String fromAccountNum, String toAccountNum) {
        if (accounts.get(fromAccountNum).getAccNumber().compareTo(accounts.get(toAccountNum).getAccNumber()) > 0) {
            return accounts.get(fromAccountNum);
        } else {
            return accounts.get(toAccountNum);
        }
    }

    private boolean isAccountExist(String nameAccount) {
        if (!accounts.containsKey(nameAccount)) {
            logger.printf(Level.INFO, inputFalseAccount, "Аккаунт: " + nameAccount + " не существует");
            return false;
        } else {
            return true;
        }
    }
}


