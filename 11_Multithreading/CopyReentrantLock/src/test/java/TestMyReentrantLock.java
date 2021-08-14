import junit.framework.TestCase;

public class TestMyReentrantLock extends TestCase {
    int c;
    MyReentrantLock lock;

    @Override
    protected void setUp() {
        c = 0;
        lock = new MyReentrantLock();
    }

    public void testLockAndUnlock() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                lock.lock();
                lock.lock();
                c++;
                lock.unlock();
                lock.unlock();
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                lock.lock();
                lock.lock();
                c--;
                lock.unlock();
                lock.unlock();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(c, 0);

    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
