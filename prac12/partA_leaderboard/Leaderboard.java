import java.util.*;

public class Leaderboard {
    public static void main(String[] args) {
        Map<String, Integer> scores = new HashMap<>();
        scores.put("Riya", 90); scores.put("Arjun", 75); scores.put("Meera", 90);
        scores.put("Kabir", 60); scores.put("Dhara", 82); scores.put("Asha", 75);
        int topN = 3;
        List<Map.Entry<String, Integer>> list = new ArrayList<>(scores.entrySet());
        list.sort((a, b) -> {
            int c = Integer.compare(b.getValue(), a.getValue());       // higher score first
            return c != 0 ? c : a.getKey().compareTo(b.getKey());      // ties by name
        });
        for (int i = 0; i < Math.min(topN, list.size()); i++) {
            System.out.println((i + 1) + ". " + list.get(i).getKey() + " - " + list.get(i).getValue());
        }
    }
}
