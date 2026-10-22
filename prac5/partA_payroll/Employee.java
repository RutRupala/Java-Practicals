public abstract class Employee {
    private final String name;
    private final int id;
    Employee(String name, int id) { this.name = name; this.id = id; }
    String getName() { return name; }
    int getId() { return id; }
    abstract double monthlySalary();
}
