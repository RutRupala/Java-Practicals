import exception.BankException;
import model.Account;
import model.SavingsAccount;
import service.AccountStore;
import service.TransactionProcessor;

public class MiniBank {
    public static void main(String[] args) throws Exception {
        AccountStore store = new AccountStore();
        store.add(new SavingsAccount("Riya", 5000, 0));
        store.add(new SavingsAccount("Arjun", 2000, 0));
        store.add(new SavingsAccount("Meera", 9000, 0));

        System.out.println("get(AC0002) -> " + store.get("AC0002"));
        System.out.println("\nSorted by balance (highest first):");
        for (Account a : store.sortedByBalance()) System.out.println("  " + a);
        System.out.println("\nSorted by account number (Comparable):");
        for (Account a : store.sortedByNumber()) System.out.println("  " + a);
        System.out.println("\nBalance above 4000: " + store.balanceAbove(4000).size() + " accounts");
        System.out.println("TreeMap keys: " + store.asTreeMap().keySet());
        System.out.println("History: " + store.getHistory());

        // shared ConcurrentHashMap inside the processor
        TransactionProcessor tp = new TransactionProcessor();
        for (Account a : store.getAll()) tp.register(a);
        for (int i = 0; i < 20; i++) {
            tp.submit(() -> { try { tp.getAccount("AC0002").deposit(100); } catch (BankException e) { } });
        }
        tp.stop();
        System.out.println("\nAC0002 after 20 concurrent deposits of 100: " + tp.getAccount("AC0002").getBalance() + " (expected 4000)");
    }
}
