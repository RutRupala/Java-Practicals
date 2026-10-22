public class Driver {
    public static void main(String[] args) {
        Fraction a = new Fraction(1, 2), b = new Fraction(2, 4), c = new Fraction(3, 6);
        System.out.println(a + " " + b + " " + c);
        System.out.println("a.equals(b): " + a.equals(b) + ", b.equals(c): " + b.equals(c));
        System.out.println("hash equal: " + (a.hashCode() == c.hashCode()));
    }
}
