public class TryDemo {
    public static void main(String[] args) {
        System.out.println("--- normal run ---");
        try (Connection c = new Connection("DB-1")) {
            c.work(false);
        } catch (RuntimeException e) {
            System.out.println("Caught: " + e.getMessage());
        }
        System.out.println("--- failing run ---");
        try (Connection c = new Connection("DB-2")) {
            c.work(true);
        } catch (RuntimeException e) {
            System.out.println("Original error reported: " + e.getMessage());
            for (Throwable t : e.getSuppressed()) System.out.println("  suppressed: " + t.getMessage());
        }
    }
}
