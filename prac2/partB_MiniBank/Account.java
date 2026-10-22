public class Account {
    private static long counter = 0;

    private final String accountNumber;
    private String ownerName;
    private long balance;
    private boolean active;

    public Account(String ownerName, long openingBalance) {
        counter++;
        this.accountNumber = String.format("AC%04d", counter);
        this.ownerName = ownerName;
        this.balance = openingBalance;
        this.active = true;
    }

    public Account(String ownerName) { this(ownerName, 0); }

    public void deposit(long amount) {
        if (amount <= 0) return;           // reject negative/zero amounts
        balance += amount;
    }

    /** Returns true when the balance was sufficient; otherwise returns false and changes nothing. */
    public boolean withdraw(long amount) {
        if (amount <= 0 || amount > balance) return false;
        balance -= amount;
        return true;
    }

    /** Helper: moves amount from this account to another using withdraw and deposit. */
    public boolean transfer(Account to, long amount) {
        if (!withdraw(amount)) return false;
        to.deposit(amount);
        return true;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName() { return ownerName; }
    public long getBalance() { return balance; }
    public boolean isActive() { return active; }
    // No public setter for balance on purpose (encapsulation).
}
