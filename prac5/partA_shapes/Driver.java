public class Driver {
    public static void main(String[] args) {
        Shape[] shapes = { new Circle(3), new Rectangle(4, 5), new Triangle(6, 7), new Circle(1.5) };
        double total = 0;
        Shape largest = shapes[0];
        for (Shape s : shapes) {                       // one loop, polymorphic call
            double a = s.area();
            total += a;
            if (a > largest.area()) largest = s;
            System.out.printf("%-10s area = %8.2f   running total = %8.2f%n", s.name(), a, total);
        }
        System.out.printf("Largest: %s (%.2f)%n", largest.name(), largest.area());
    }
}
