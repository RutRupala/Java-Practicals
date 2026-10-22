public class Magazine extends Media {
    Magazine(String t) { super(t); }
    double lateFee(int d) { return Math.min(1.0 * d, 10.0); }
}
