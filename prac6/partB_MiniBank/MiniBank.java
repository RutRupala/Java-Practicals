import model.*;
import service.StatementFormatter;
import util.CommandParser;
import util.Command;

import static util.Validator.isValidMobile;     // one static import

public class MiniBank {
    public static void main(String[] args) {
        Account savings = new SavingsAccount("Riya", 5000, 1000);
        Account fd = new FixedDepositAccount("Meera", 10000);

        System.out.println("Yearly interest (savings): " + savings.yearlyInterest());
        System.out.println("Quarterly interest (savings): " + savings.quarterlyInterest());
        System.out.println("FD is Premium: " + (fd instanceof Premium) + ", savings is Premium: " + (savings instanceof Premium));

        // WithdrawRule as an anonymous class ...
        WithdrawRule anon = new WithdrawRule() {
            @Override public boolean allow(Account account, long amount) {
                return amount <= 2000 && account.canWithdraw(amount);
            }
        };
        // ... and as a lambda
        WithdrawRule lambda = (account, amount) -> amount <= 2000 && account.canWithdraw(amount);

        long[] tests = { 1500, 3000 };
        for (long amt : tests) {
            System.out.println("withdraw " + amt + " -> anonymous: " + anon.allow(savings, amt)
                    + ", lambda: " + lambda.allow(savings, amt));
        }

        System.out.println("isValidMobile(9876543210) = " + isValidMobile("9876543210"));
        Command c = CommandParser.parse("DEPOSIT AC0001 500");
        System.out.println(c);
        System.out.println(StatementFormatter.buildStatement(savings));
    }
}
