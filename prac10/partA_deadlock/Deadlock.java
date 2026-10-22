public class Deadlock {
    static void pause() { try { Thread.sleep(200); } catch (InterruptedException e) { } }

    static void lockInOrder(Object first, Object second, String who) {
        synchronized (first) {
            System.out.println(who + " got first lock");
            pause();
            synchronized (second) { System.out.println(who + " got both locks"); }
        }
    }

    static boolean run(boolean consistentOrder) throws InterruptedException {
        final Object lockA = new Object(), lockB = new Object();   // fresh locks each run
        Thread t1 = new Thread(() -> lockInOrder(lockA, lockB, "T1"));
        Thread t2 = consistentOrder
                ? new Thread(() -> lockInOrder(lockA, lockB, "T2"))     // same order -> safe
                : new Thread(() -> lockInOrder(lockB, lockA, "T2"));    // opposite order -> deadlock
        t1.setDaemon(true); t2.setDaemon(true);
        t1.start(); t2.start();
        t1.join(1500); t2.join(1500);
        return t1.isAlive() || t2.isAlive();
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("--- opposite lock order ---");
        System.out.println(run(false) ? "DEADLOCK detected (threads stuck)" : "finished");
        System.out.println("--- consistent lock order ---");
        System.out.println(run(true) ? "DEADLOCK detected" : "finished normally");
    }
}
