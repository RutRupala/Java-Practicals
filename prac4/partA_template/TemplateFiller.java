import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateFiller {
    static String fill(String template, String[] names, String[] values) {
        Pattern p = Pattern.compile("\\{(\\w+)\\}");
        Matcher m = p.matcher(template);
        StringBuilder sb = new StringBuilder();
        int last = 0;
        while (m.find()) {
            sb.append(template, last, m.start());
            String key = m.group(1), val = "[?]";
            for (int i = 0; i < names.length; i++) if (names[i].equals(key)) { val = values[i]; break; }
            sb.append(val);
            last = m.end();
        }
        sb.append(template.substring(last));
        return sb.toString();
    }
}
