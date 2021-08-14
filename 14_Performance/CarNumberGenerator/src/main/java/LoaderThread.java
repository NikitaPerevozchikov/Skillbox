import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LoaderThread {

    public static void startGeneration() throws Exception {
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 2; i++) {
            MyRunnable task = new MyRunnable(i);
            service.execute(task);
        }
        service.shutdown();
        service.awaitTermination(1, TimeUnit.HOURS);
    }

}
