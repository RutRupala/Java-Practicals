import java.util.concurrent.atomic.AtomicInteger;

public class SeatBooking {
    static int seatsLeft;
    static final AtomicInteger success = new AtomicInteger();

    static void unsafeBook() {
        if (seatsLeft > 0) {
            try { Thread.sleep(5); } catch (InterruptedException e) { }   // widen the race window
            seatsLeft--;
            success.incrementAndGet();
        }
    }

    static synchronized void safeBook() {
        if (seatsLeft > 0) {
            try { Thread.sleep(5); } catch (InterruptedException e) { }
            seatsLeft--;
            success.incrementAndGet();
        }
    }

    static int run(Runnable r) throws InterruptedException {
        seatsLeft = 5; success.set(0);
        Thread[] ts = new Thread[10];
        for (int i = 0; i < ts.length; i++) { ts[i] = new Thread(r); ts[i].start(); }
        for (Thread t : ts) t.join();
        return success.get();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Unsynchronized: " + run(SeatBooking::unsafeBook) + " bookings succeeded (only 5 seats!)");
        System.out.println("Synchronized  : " + run(SeatBooking::safeBook) + " bookings succeeded");
    }
}
