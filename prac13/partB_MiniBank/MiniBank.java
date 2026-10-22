import model.Account;
import model.CurrentAccount;
import model.Customer;
import model.FixedDepositAccount;
import model.SavingsAccount;
import service.Repository;

public class MiniBank {
    public static void main(String[] args) {
        Repository<Account> repo = new Repository<>();
        Account a1 = new SavingsAccount("Riya", 5000, 1000);
        Account a2 = new CurrentAccount("Arjun", 2000, 3000);
        Account a3 = new FixedDepositAccount("Meera", 10000);
        repo.saveAll(a1, a2, a3);

        System.out.println("findById(" + a2.getAccountNumber() + ") -> " + repo.findById(a2.getAccountNumber()));
        System.out.println("All accounts (" + repo.count() + "):");
        Repository.printAll(repo.findAll());
        System.out.println("delete(" + a3.getAccountNumber() + ") -> " + repo.delete(a3.getAccountNumber()) + ", left " + repo.count());

        // the SAME class works for Customer, unchanged
        Repository<Customer> customers = new Repository<>();
        Customer c = new Customer("Riya", "riya@example.com", "9876543210");
        customers.save(c);
        System.out.println("Customer by id: " + customers.findById(c.getCustomerId()).getName());

        System.out.println("average(Integer[]) = " + Repository.average(new Integer[]{ 10, 20, 30 }));
        System.out.println("average(Double[])  = " + Repository.average(new Double[]{ 1.5, 2.5 }));
    }
}
