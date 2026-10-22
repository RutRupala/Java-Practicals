import java.util.Scanner;

public class TaskApp {
    public static void main(String[] args) {
        TaskService service = new TaskService();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\n1) Add  2) Complete  3) List  4) Exit > ");
            if (!sc.hasNextLine()) break;
            String choice = sc.nextLine().trim();
            try {
                switch (choice) {
                    case "1" -> { System.out.print("Title: "); System.out.println("Added " + service.add(sc.nextLine())); }
                    case "2" -> { System.out.print("Id: "); service.complete(Integer.parseInt(sc.nextLine().trim())); System.out.println("Done."); }
                    case "3" -> service.list().forEach(System.out::println);
                    case "4" -> { System.out.println("Bye!"); return; }
                    default -> System.out.println("Invalid choice");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
