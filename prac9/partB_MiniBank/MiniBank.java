import model.Account;
import model.SavingsAccount;
import service.AccountWorker;

public class MiniBank {
    static long runDemo(boolean safe) throws InterruptedException {
        Account acc = new SavingsAccount("Riya", 0, 0);
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new AccountWorker(acc, 1000, 1, safe), "Worker-" + (i + 1));
            System.out.println(threads[i].getName() + " state before start: " + threads[i].getState());   // NEW
        }
        for (Thread t : threads) t.start();
        System.out.println(threads[0].getName() + " state after start : " + threads[0].getState());       // RUNNABLE/...
        for (Thread t : threads) t.join();
        System.out.println(threads[0].getName() + " state after join  : " + threads[0].getState());       // TERMINATED
        return acc.getBalance();
    }

    public static void main(String[] args) throws InterruptedException {
        long wrong = runDemo(false);
        System.out.println("WITHOUT synchronization, balance = " + wrong + " (expected 10000)\n");
        long right = runDemo(true);
        System.out.println("WITH synchronized deposit, balance = " + right + " (expected 10000)");
    }
}
