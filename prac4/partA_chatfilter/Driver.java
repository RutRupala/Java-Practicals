import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        String[] log = { "10:05 alice Hello there", "10:06 bob", "10:07 carol See you at lunch" };
        System.out.print("Keyword: ");
        String keyword = new Scanner(System.in).nextLine().trim();
        StringBuilder sb = new StringBuilder();
        int n = ChatFilter.filter(log, keyword, sb);
        System.out.println("Matches: " + n);
        System.out.print(sb);
    }
}
