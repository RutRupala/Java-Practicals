package model;

public abstract class Account implements Transactable, InterestBearing {
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
        if (amount <= 0 || !canWithdraw(amount)) return false;
        balance -= amount;
        return true;
    }

    /** Helper: moves amount from this account to another using withdraw and deposit. */
    public boolean transfer(Account to, long amount) {
        if (!withdraw(amount)) return false;
        to.deposit(amount);
        return true;
    }

    /** Annual interest rate in percent - each account type decides. */
    public abstract double interestRate();

    /** Each account type decides whether this withdrawal is allowed. */
    public abstract boolean canWithdraw(long amount);

    public double monthlyInterest() { return balance * interestRate() / 100.0 / 12.0; }

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
