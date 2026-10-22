package service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TransactionProcessor {
    private final ExecutorService pool = Executors.newFixedThreadPool(4);
    private final Map<String, Integer> tasksPerThread = new ConcurrentHashMap<>();

    public void submit(Runnable task) {
        pool.execute(() -> {
            tasksPerThread.merge(Thread.currentThread().getName(), 1, Integer::sum);
            task.run();
        });
    }

    public void stop() throws InterruptedException {
        pool.shutdown();
        if (!pool.awaitTermination(30, TimeUnit.SECONDS)) pool.shutdownNow();
    }

    public Map<String, Integer> getTasksPerThread() { return tasksPerThread; }
}
