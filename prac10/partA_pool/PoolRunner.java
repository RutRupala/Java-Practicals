import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PoolRunner {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        for (int i = 1; i <= 10; i++) {
            final int id = i;
            pool.execute(() -> {
                System.out.println("Task " + id + " running on " + Thread.currentThread().getName());
                try { Thread.sleep(200); } catch (InterruptedException e) { }
            });
        }
        pool.shutdown();
        pool.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("All tasks done - note only pool-1-thread-1..3 were used");
    }
}
