import java.util.concurrent.atomic.AtomicLong;

public class SplitSum {
    static long unsafeTotal = 0;
    static long syncTotal = 0;
    static final AtomicLong atomicTotal = new AtomicLong();
    static final Object LOCK = new Object();

    interface Adder { void add(long partial); }

    static long run(long[] data, int threads, Adder adder) throws InterruptedException {
        Thread[] ts = new Thread[threads];
        int chunk = data.length / threads;
        for (int t = 0; t < threads; t++) {
            final int from = t * chunk, to = (t == threads - 1) ? data.length : from + chunk;
            ts[t] = new Thread(() -> {
                for (int i = from; i < to; i++) adder.add(data[i]);   // add element by element
            });
        }
        long start = System.nanoTime();
        for (Thread t : ts) t.start();
        for (Thread t : ts) t.join();
        return System.nanoTime() - start;
    }

    public static void main(String[] args) throws Exception {
        long[] data = new long[400_000];
        for (int i = 0; i < data.length; i++) data[i] = 1;      // true sum = 400000
        long n1 = run(data, 4, v -> { long t = unsafeTotal; Thread.yield(); unsafeTotal = t + v; });
        System.out.println("Unsafe total  : " + unsafeTotal + "  (wrong)");
        long n2 = run(data, 4, v -> { synchronized (LOCK) { syncTotal += v; } });
        System.out.println("Synchronized  : " + syncTotal + "  time " + n2 / 1_000_000 + " ms");
        long n3 = run(data, 4, atomicTotal::addAndGet);
        System.out.println("AtomicLong    : " + atomicTotal.get() + "  time " + n3 / 1_000_000 + " ms");
        System.out.println("(Measured with System.nanoTime around start..join of all threads)");
    }
}
