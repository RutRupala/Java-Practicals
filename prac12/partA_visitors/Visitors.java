import java.util.*;

public class Visitors {
    public static void main(String[] args) {
        List<String> logins = List.of("riya", "arjun", "riya", "meera", "arjun", "kabir", "meera", "riya");
        Set<String> unique = new HashSet<>(logins);
        Set<String> ordered = new LinkedHashSet<>(logins);
        System.out.println("Unique users: " + unique.size());
        int shown = 0;
        System.out.print("First three distinct (first-seen order): ");
        for (String s : ordered) { if (shown++ == 3) break; System.out.print(s + " "); }
        System.out.println();
    }
}
