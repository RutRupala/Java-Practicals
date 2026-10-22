public class ChatFilter {
    /** Returns the number of matches; the report is appended into sb. */
    static int filter(String[] lines, String keyword, StringBuilder sb) {
        int count = 0;
        for (String line : lines) {
            String[] parts = line.split(" ", 3);
            if (parts.length < 3) continue;               // skip malformed lines
            if (parts[2].toLowerCase().contains(keyword.toLowerCase())) {
                count++;
                sb.append(parts[0]).append(' ').append(parts[1]).append(": ").append(parts[2]).append('\n');
            }
        }
        return count;
    }
}
