public abstract class Shape {
    abstract double area();
    String name() { return getClass().getSimpleName(); }
}
