public class Book extends Media {
    Book(String t) { super(t); }
    double lateFee(int d) { return 2.0 * d; }
}
