import java.util.HashSet;
import java.util.Set;

public class MyReentrantLock {
    private boolean lockOn;
    final Set<Thread> listThread;

    public MyReentrantLock() {
        lockOn = false;
        listThread = new HashSet<>();
    }

    public synchronized void lock() {
        if (!addInList(Thread.currentThread(), listThread)) {
            return;
        }
        while (lockOn) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lockOn = true;
    }

    public synchronized void unlock() {
        if (!deleteInList(Thread.currentThread(), listThread)) {
            return;
        }
        notify();
        lockOn = false;
    }

    private boolean addInList(Thread thread, Set<Thread> listThread) {
        return listThread.add(thread);
    }

    private boolean deleteInList(Thread thread, Set<Thread> listThread) {
        return listThread.remove(thread);
    }
}
