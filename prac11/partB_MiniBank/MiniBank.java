import model.Account;
import model.CurrentAccount;
import model.FixedDepositAccount;
import model.SavingsAccount;
import service.ReportGenerator;
import service.StatePersister;
import service.TransactionLog;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MiniBank {
    public static void main(String[] args) throws Exception {
        Path data = Paths.get("data");
        Path logs = data.resolve("logs");
        Files.createDirectories(logs);

        // ---- save and reload ----
        Account[] accounts = {
            new SavingsAccount("Riya", 5000, 1000),
            new CurrentAccount("Arjun", 2000, 3000),
            new FixedDepositAccount("Meera", 10000)
        };
        StatePersister.save(accounts, data.resolve("accounts.dat"));
        Account[] reloaded = StatePersister.load(data.resolve("accounts.dat"));
        System.out.println("Reloaded accounts:");
        for (Account a : reloaded) System.out.println("  " + a);

        // ---- transaction logs (start fresh for the demo) ----
        for (Path p : new Path[]{ logs.resolve("day1.log"), logs.resolve("day2.log"), logs.resolve("empty.log"), logs.resolve("notes.txt") }) {
            Files.deleteIfExists(p);
        }
        TransactionLog.append(logs.resolve("day1.log"), "DEPOSIT AC0001 500");
        TransactionLog.append(logs.resolve("day1.log"), "WITHDRAW AC0002 200");
        TransactionLog.append(logs.resolve("day2.log"), "DEPOSIT AC0003 1000");
        TransactionLog.append(logs.resolve("day2.log"), "WITHDRAW AC0001 300");
        Files.createFile(logs.resolve("empty.log"));                  // will be skipped
        Files.writeString(logs.resolve("notes.txt"), "DEPOSIT AC0001 99999\n");   // not .log -> skipped

        // ---- report ----
        String report = ReportGenerator.generate(logs, data.resolve("report.txt"));
        System.out.println();
        System.out.println(report);
        System.out.println("(expected: deposits 1500, withdrawals 500, net 1000)");
    }
}
