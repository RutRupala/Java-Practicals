package discount;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DiscountEngine {
    public static void main(String[] args) {
        Map<String, DiscountRule> rules = new LinkedHashMap<>();
        rules.put("none", p -> p);
        rules.put("10off", p -> p * 0.90);
        rules.put("flat50", p -> Math.max(0, p - 50));
        rules.put("bulk", p -> p > 1000 ? p * 0.80 : p);

        List<Double> prices = List.of(200.0, 999.0, 1500.0, 40.0);
        Scanner sc = new Scanner(System.in);
        System.out.println("Available rules: " + rules.keySet());
        System.out.print("Pick a rule: ");
        DiscountRule rule = rules.get(sc.next());
        if (rule == null) { System.out.println("Unknown rule."); return; }
        for (double p : prices) {
            System.out.printf("%.2f -> %.2f%n", p, rule.apply(p));
        }
    }
}
