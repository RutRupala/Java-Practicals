import java.util.Objects;

public class Fraction {
    private int num, den;
    public Fraction(int num, int den) {
        if (den == 0) throw new IllegalArgumentException("Denominator cannot be zero");
        int g = gcd(Math.abs(num), Math.abs(den));
        if (g == 0) g = 1;
        num /= g; den /= g;
        if (den < 0) { num = -num; den = -den; }
        this.num = num; this.den = den;
    }
    private static int gcd(int a, int b) { return b == 0 ? a : gcd(b, a % b); }
    @Override public String toString() { return num + "/" + den; }
    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fraction)) return false;
        Fraction f = (Fraction) o;
        return num == f.num && den == f.den;
    }
    @Override public int hashCode() { return Objects.hash(num, den); }
}
