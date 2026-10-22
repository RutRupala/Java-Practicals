public class Driver {
    public static void main(String[] args) {
        Employee[] staff = { new FullTime("Asha", 1, 60000), new PartTime("Bhavin", 2, 80, 250),
                             new Intern("Chirag", 3, 12000), new FullTime("Dhara", 4, 75000) };
        double total = 0;
        for (Employee e : staff) {
            double pay = e.monthlySalary();
            total += pay;
            String note = (e instanceof Intern) ? "  (intern - stipend only)" : "";
            System.out.printf("%d %-8s %10.2f%s%n", e.getId(), e.getName(), pay, note);
        }
        System.out.printf("Total payroll: %.2f%n", total);
    }
}
