public class MiniBank {
    public static void main(String[] args) {
        Customer c = new Customer("Riya", "riya@example.com", "9876543210");
        System.out.println("Customer " + c.getCustomerId() + " : " + c.getName());

        Account[] accounts = { new Account("Riya", 5000), new Account("Arjun", 2000), new Account("Meera") };
        accounts[0].deposit(2000);
        System.out.println(accounts[0].getAccountNumber() + " after deposit(2000): " + accounts[0].getBalance());
        System.out.println("withdraw(3000): " + accounts[0].withdraw(3000) + ", balance = " + accounts[0].getBalance());
        System.out.println("withdraw(10000): " + accounts[0].withdraw(10000) + ", balance = " + accounts[0].getBalance());
        accounts[1].deposit(-50);                       // rejected
        accounts[1].transfer(accounts[2], 500);
        System.out.println("\nAll balances:");
        for (Account a : accounts) {
            System.out.println(a.getAccountNumber() + " " + a.getOwnerName() + " -> " + a.getBalance());
        }
    }
}
