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

    /** Copy constructor: creates an object with the SAME account number (used to test equals). */
    public Account(Account other) {
        this.accountNumber = other.accountNumber;
        this.ownerName = other.ownerName;
        this.balance = other.balance;
        this.active = other.active;
    }

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

    /** Formatted multi-line statement. */
    public String statement() {
        return "Account Statement\n-----------------\nNumber : " + accountNumber
                + "\nOwner  : " + ownerName + "\nBalance: " + balance
                + "\nStatus : " + (active ? "ACTIVE" : "INACTIVE");
    }

    public String toString() {
        return accountNumber + " | " + ownerName + " | " + balance + " | " + (active ? "active" : "inactive");
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        return accountNumber.equals(((Account) o).accountNumber);
    }

    public int hashCode() { return accountNumber.hashCode(); }
}
