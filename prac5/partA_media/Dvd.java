public class Dvd extends Media {
    Dvd(String t) { super(t); }
    double lateFee(int d) { return 5.0 * d + (d > 7 ? 20 : 0); }
}
