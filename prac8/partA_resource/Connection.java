public class Connection implements AutoCloseable {
    private final String name;
    Connection(String name) { this.name = name; System.out.println("Opened " + name); }
    void work(boolean fail) {
        System.out.println(name + " working...");
        if (fail) throw new IllegalStateException("work failed in " + name);
    }
    @Override public void close() {
        System.out.println("Closed " + name);
        throw new RuntimeException("problem while closing " + name);   // to show suppressed exceptions
    }
}
