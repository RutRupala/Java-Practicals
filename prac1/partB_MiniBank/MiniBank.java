import java.util.Scanner;

public class MiniBank {
    record BankInfo(String name, String branch) { }

    enum MenuOption { OPEN_ACCOUNT, DEPOSIT, WITHDRAW, TRANSFER, EXIT }

    public static void main(String[] args) {
        BankInfo info = new BankInfo("MiniBank", "Charusat Campus Branch");
        System.out.println("==== " + info + " ====");
        MenuOption[] options = MenuOption.values();
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("\nMenu:");
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i]);
            }
            System.out.print("Choose an option: ");
            if (!sc.hasNextLine()) break;
            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number between 1 and " + options.length);
                continue;
            }
            if (choice < 1 || choice > options.length) {   // re-prompt on invalid number
                System.out.println("Invalid option. Please enter a number between 1 and " + options.length);
                continue;
            }
            MenuOption selected = options[choice - 1];
            String msg = switch (selected) {
                case OPEN_ACCOUNT -> "Open Account - to be implemented in a later lab";
                case DEPOSIT -> "Deposit - to be implemented in a later lab";
                case WITHDRAW -> "Withdraw - to be implemented in a later lab";
                case TRANSFER -> "Transfer - to be implemented in a later lab";
                case EXIT -> "Thank you for using MiniBank. Goodbye!";
            };
            System.out.println(msg);
            if (selected == MenuOption.EXIT) running = false;
        }
    }
}
