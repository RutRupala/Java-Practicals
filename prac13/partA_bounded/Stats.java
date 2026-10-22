public class Stats {
    static <N extends Number> double average(N[] values) {
        double sum = 0;
        for (N v : values) sum += v.doubleValue();
        return values.length == 0 ? 0 : sum / values.length;
    }

    public static void main(String[] args) {
        System.out.println(average(new Integer[]{ 1, 2, 3, 4 }));
        System.out.println(average(new Double[]{ 1.5, 2.5 }));
        System.out.println(average(new Long[]{ 10L, 20L, 30L }));
        // average(new String[]{ "a" });   // compile error: String is not a Number (bounded type)
    }
}
