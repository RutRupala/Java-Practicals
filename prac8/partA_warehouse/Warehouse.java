import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    private final Map<String, Integer> stock = new HashMap<>();

    Warehouse() { stock.put("pen", 10); stock.put("book", 3); stock.put("bag", 1); }

    void issue(String item, int qty) throws OutOfStockException {
        if (qty <= 0) throw new InvalidQuantityException(qty);
        int have = stock.getOrDefault(item, 0);
        if (qty > have) throw new OutOfStockException(item, qty - have);
        stock.put(item, have - qty);
        System.out.println("Issued " + qty + " x " + item + " (left " + (have - qty) + ")");
    }

    public static void main(String[] args) {
        Warehouse w = new Warehouse();
        Object[][] requests = { {"pen", 4}, {"book", 5}, {"bag", 0}, {"bag", 1}, {"laptop", 1}, {"pen", -2} };
        for (Object[] r : requests) {
            try {
                w.issue((String) r[0], (Integer) r[1]);
            } catch (OutOfStockException e) {
                System.out.println("FAILED: " + e.getMessage() + " [shortfall=" + e.getShortfall() + "]");
            } catch (InvalidQuantityException e) {
                System.out.println("FAILED: " + e.getMessage());
            }
        }
    }
}
