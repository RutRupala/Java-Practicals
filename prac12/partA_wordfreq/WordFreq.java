import java.util.*;

public class WordFreq {
    public static void main(String[] args) {
        String text = "the quick brown fox jumps over the lazy dog the fox barks and the dog runs";
        Map<String, Integer> freq = new HashMap<>();
        for (String w : text.toLowerCase().split("\\s+")) freq.merge(w, 1, Integer::sum);

        List<Map.Entry<String, Integer>> entries = new ArrayList<>(freq.entrySet());
        entries.sort(Comparator.comparing((Map.Entry<String, Integer> e) -> e.getValue()).reversed()
                .thenComparing(Map.Entry::getKey));
        for (Map.Entry<String, Integer> e : entries) System.out.println(e.getKey() + " : " + e.getValue());
    }
}
