public class MiniBank {
    public static void main(String[] args) {
        Account[] accounts = {
            new SavingsAccount("Riya", 5000, 1000),
            new CurrentAccount("Arjun", 2000, 3000),
            new FixedDepositAccount("Meera", 10000)
        };
        for (Account a : accounts) {                      // run-time polymorphism
            System.out.println(a.getAccountNumber() + " (" + a.getClass().getSimpleName()
                    + ") interestRate = " + a.interestRate() + ", monthly interest = "
                    + String.format("%.2f", a.monthlyInterest()));
            if (a instanceof CurrentAccount c) {          // pattern matching instanceof
                System.out.println("   overdraft limit = " + c.getOverdraftLimit());
            }
        }
        System.out.println("Savings withdraw(4500): " + accounts[0].withdraw(4500) + " (would go below min balance)");
        System.out.println("Savings withdraw(3000): " + accounts[0].withdraw(3000));
        System.out.println("Current withdraw(4000): " + accounts[1].withdraw(4000) + " -> balance " + accounts[1].getBalance());
        System.out.println("Current withdraw(2000): " + accounts[1].withdraw(2000) + " (over overdraft limit)");
        System.out.println("FD withdraw(1): " + accounts[2].withdraw(1) + " (locked)");
    }
}
