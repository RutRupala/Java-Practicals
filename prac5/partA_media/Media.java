public abstract class Media {
    protected final String title;
    Media(String title) { this.title = title; }
    abstract double lateFee(int daysLate);
}
