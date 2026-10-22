import exception.BankException;
import exception.InsufficientFundsException;
import model.Account;
import model.CurrentAccount;
import model.SavingsAccount;
import util.AuditSession;

public class MiniBank {
    public static void main(String[] args) {
        Account a = new SavingsAccount("Riya", 1000, 0);
        Account b = new CurrentAccount("Arjun", 500, 1000);

        // 1. Insufficient funds -> shortfall carried inside the exception
        try {
            a.withdraw(5000);
        } catch (InsufficientFundsException e) {
            System.out.println("Withdrawal failed: short by " + e.getShortfall());
        } catch (BankException e) {
            System.out.println("Bank error: " + e.getMessage());
        } finally {
            System.out.println("Balance still " + a.getBalance());
        }

        // 2. Invalid amounts
        try { a.deposit(-10); } catch (BankException e) { System.out.println("Deposit rejected: " + e.getMessage()); }
        try { a.withdraw(0); } catch (BankException e) { System.out.println("Withdraw rejected: " + e.getMessage()); }

        // 3. Daily limit
        try { b.deposit(100000); b.withdraw(60000); }
        catch (BankException e) { System.out.println("Limit: " + e.getMessage()); }

        // 4. Transfer (success, then failure with rethrow)
        try { a.transfer(b, 400); } catch (BankException e) { System.out.println("unexpected"); }
        try { a.transfer(b, 9999); } catch (BankException e) { System.out.println("Caller caught: " + e.getMessage()); }
        System.out.println("Balances: " + a.getBalance() + " / " + b.getBalance());

        // 5. try-with-resources with an AutoCloseable class of our own
        try (AuditSession s = new AuditSession("teller-1")) {
            s.log("processing withdrawal");
            a.withdraw(99999);
        } catch (BankException e) {
            System.out.println("Session ended with error: " + e.getMessage());
        }
    }
}
