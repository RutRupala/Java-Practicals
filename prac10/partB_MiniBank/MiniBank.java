import exception.BankException;
import model.Account;
import model.SavingsAccount;
import service.TransactionBuffer;
import service.TransactionProcessor;
import service.TransferDemo;
import util.Command;
import util.TransactionType;

public class MiniBank {
    public static void main(String[] args) throws Exception {
        // ---- 1. Thread pool processing a batch ----
        Account acc = new SavingsAccount("Riya", 0, 0);
        TransactionProcessor processor = new TransactionProcessor();
        for (int i = 0; i < 40; i++) {
            processor.submit(() -> { try { acc.deposit(100); } catch (BankException e) { } });
        }
        for (int i = 0; i < 10; i++) {
            processor.submit(() -> { try { acc.withdraw(50); } catch (BankException e) { } });
        }
        processor.stop();
        System.out.println("Batch done. Balance = " + acc.getBalance() + " (expected 40*100 - 10*50 = 3500)");
        System.out.println("Tasks per pool thread: " + new java.util.TreeMap<>(processor.getTasksPerThread()));

        // ---- 2. Producer / consumer with wait()/notify() ----
        Account target = new SavingsAccount("Arjun", 0, 0);
        TransactionBuffer buffer = new TransactionBuffer();
        final int N = 10;
        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= N; i++) {
                    buffer.put(new Command(TransactionType.DEPOSIT, target.getAccountNumber(), i * 10));
                }
            } catch (InterruptedException e) { }
        }, "producer");
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= N; i++) {
                    Command c = buffer.take();
                    target.deposit(c.amount());
                    System.out.println("Processed " + c);
                }
            } catch (InterruptedException | BankException e) { }
        }, "consumer");
        producer.start(); consumer.start();
        producer.join(); consumer.join();
        System.out.println("Consumer total = " + target.getBalance() + " (expected 550)");

        // ---- 3. Deadlock created, then fixed ----
        Account a = new SavingsAccount("A", 1000, 0);
        Account b = new SavingsAccount("B", 1000, 0);
        System.out.println("\nTransfer A->B and B->A with opposite lock order:");
        System.out.println(TransferDemo.run(a, b, false) ? "  -> DEADLOCK (threads stuck)" : "  -> finished");
        Account c = new SavingsAccount("C", 1000, 0);
        Account d = new SavingsAccount("D", 1000, 0);
        System.out.println("Same transfers with lower-account-number-first locking:");
        boolean stuck = TransferDemo.run(c, d, true);
        System.out.println(stuck ? "  -> DEADLOCK" : "  -> finished. C=" + c.getBalance() + ", D=" + d.getBalance());
    }
}
