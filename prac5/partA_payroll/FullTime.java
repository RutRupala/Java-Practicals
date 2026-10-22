public class FullTime extends Employee {
    private double fixed;
    FullTime(String name, int id, double fixed) { super(name, id); this.fixed = fixed; }
    double monthlySalary() { return fixed; }
}
