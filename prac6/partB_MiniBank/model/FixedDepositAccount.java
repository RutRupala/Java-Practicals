package model;

public class FixedDepositAccount extends Account implements Premium {
    public FixedDepositAccount(String ownerName, long amount) { super(ownerName, amount); }

    public double interestRate() { return 7.0; }
    public boolean canWithdraw(long amount) { return false; }   // deposit is locked
}
