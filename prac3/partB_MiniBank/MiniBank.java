public class MiniBank {
    public static void main(String[] args) {
        Account a1 = new Account("Riya", 5000);
        a1.deposit(2000);
        a1.withdraw(3000);
        Account a2 = new Account(a1);          // same account number
        Account a3 = new Account("Arjun", 100);

        System.out.println(a1);                // readable toString
        System.out.println("a1.equals(a2): " + a1.equals(a2));
        System.out.println("a1.equals(a3): " + a1.equals(a3));
        System.out.println("hashCodes equal (a1,a2): " + (a1.hashCode() == a2.hashCode()));

        Object o = a1;
        System.out.println("o instanceof Account: " + (o instanceof Account));
        System.out.println("o instanceof Customer: " + (o instanceof Customer));
        System.out.println(a1.statement());

        Customer c = new Customer("Riya", "riya@example.com", "9876543210");
        c.setAddress(new Customer.Address("12 MG Road", "Anand", "388001"));
        Customer copy = c.clone();
        System.out.println(c.getCustomerId() + " lives at " + c.getAddress());
        System.out.println("clone has same id: " + copy.getCustomerId().equals(c.getCustomerId())
                + ", same address: " + copy.getAddress());
    }
}
