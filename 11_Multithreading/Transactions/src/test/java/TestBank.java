import junit.framework.TestCase;

import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TestBank extends TestCase {
    HashMap<String, Account> accounts = new HashMap<>();
    AtomicInteger count = new AtomicInteger();
    Bank bank = new Bank(accounts);
    long countInBank;

    @Override
    protected void setUp() {

        for (int i = 1; i <= 10; i++) {
            String accNumber = i + "";
            accounts.put(accNumber, new Account(accNumber));
        }
        countInBank = bank.getBalanceInBank(accounts);
    }

    public void testTransfer() throws InterruptedException {
        Runnable task = () -> {
            for (int j = 1; j <= 1000; j++) {
                String oneAccount = "";
                String twoAccount = "";
                while (!accounts.containsKey(oneAccount)) {
                    oneAccount = "" + (int) (10 * Math.random());
                }
                while (!accounts.containsKey(twoAccount) && !oneAccount.equals(twoAccount)) {
                    twoAccount = "" + (int) (10 * Math.random());
                }
                long amount = (long) (700 * Math.random());
                bank.transfer(oneAccount, twoAccount, amount, countInBank);
                count.incrementAndGet();
            }
        };

        ExecutorService service = Executors.newCachedThreadPool();

        for (int i = 1; i <= 5; i++) {
            service.execute(task);
        }

        service.shutdown();
        service.awaitTermination(1, TimeUnit.HOURS);

        assertEquals(count.intValue(), 5000);
        assertEquals(bank.getBalanceInBank(accounts), countInBank);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
