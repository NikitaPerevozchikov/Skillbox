public class Account implements Comparable <Account> {
    private long money;
    private String accNumber;
    private volatile boolean locked;

    public Account(String accNumber) {
        money = 1000;
        this.accNumber = accNumber;
        locked = false;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public synchronized long getMoney() {
        return money;
    }

    public synchronized void subtractMoney(long sum) {
        money-= sum;
    }

    public synchronized void addMoney(long sum) {
        money += sum;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean status) {
        this.locked = status;
    }

    @Override
    public int compareTo(Account o) {
        return this.getAccNumber().compareTo(o.getAccNumber());
    }
}
