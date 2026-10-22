public class PartTime extends Employee {
    private int hours; private double rate;
    PartTime(String name, int id, int hours, double rate) { super(name, id); this.hours = hours; this.rate = rate; }
    double monthlySalary() { return hours * rate; }
}
