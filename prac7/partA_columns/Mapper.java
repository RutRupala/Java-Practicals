import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class Mapper {
    /** Missing column -> field keeps its default value and a warning is printed. */
    static <T> T map(Class<T> type, String[] header, String[] row) throws Exception {
        T obj = type.getDeclaredConstructor().newInstance();
        List<String> cols = Arrays.asList(header);
        for (Field f : type.getDeclaredFields()) {
            Column c = f.getAnnotation(Column.class);
            if (c == null) continue;
            int idx = cols.indexOf(c.value());
            if (idx < 0 || idx >= row.length) { System.out.println("  warning: no column '" + c.value() + "'"); continue; }
            f.setAccessible(true);
            if (f.getType() == int.class) f.setInt(obj, Integer.parseInt(row[idx].trim()));
            else f.set(obj, row[idx].trim());
        }
        return obj;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(map(Person.class, new String[]{"city", "name", "age"}, new String[]{"Anand", "Riya", "19"}));
        System.out.println(map(Person.class, new String[]{"name", "age"}, new String[]{"Arjun", "20"}));
    }
}
