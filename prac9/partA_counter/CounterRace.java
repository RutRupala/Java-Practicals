public class CounterRace {
    static int unsafeCount = 0;
    static int safeCount = 0;
    static final Object LOCK = new Object();

    static void unsafeInc() { int t = unsafeCount; Thread.yield(); unsafeCount = t + 1; }
    static synchronized void safeInc() { safeCount++; }

    static void runThreads(Runnable r, int n) throws InterruptedException {
        Thread[] ts = new Thread[n];
        for (int i = 0; i < n; i++) { ts[i] = new Thread(r, "T-" + i); ts[i].start(); }
        for (Thread t : ts) t.join();
    }

    public static void main(String[] args) throws Exception {
        int threads = 8, per = 10000;
        runThreads(() -> { for (int i = 0; i < per; i++) unsafeInc(); }, threads);
        System.out.println("Without sync: " + unsafeCount + " (expected " + threads * per + ")");
        runThreads(() -> { for (int i = 0; i < per; i++) safeInc(); }, threads);
        System.out.println("With sync   : " + safeCount + " (expected " + threads * per + ")");
    }
}
