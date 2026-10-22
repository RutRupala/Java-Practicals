public class CinemaShow {
    private String title;
    private int seatsAvailable;
    private final int capacity;
    private static int totalBooked = 0;

    public CinemaShow(String title, int capacity) {
        this.title = title;
        this.capacity = capacity;
        this.seatsAvailable = capacity;
    }

    public CinemaShow(String title) { this(title, 100); }

    public boolean book(int n) {
        if (n <= seatsAvailable) {
            seatsAvailable -= n;
            totalBooked += n;
            return true;
        }
        return false;
    }

    public void cancel(int n) {
        seatsAvailable = Math.min(capacity, seatsAvailable + n);
    }

    public int getSeatsAvailable() { return seatsAvailable; }
    public static int getTotalBooked() { return totalBooked; }

    public static void main(String[] args) {
        CinemaShow show = new CinemaShow("Interstellar", 50);
        System.out.println("book(20): " + show.book(20) + ", seats = " + show.getSeatsAvailable());
        System.out.println("book(40): " + show.book(40) + ", seats = " + show.getSeatsAvailable());
        show.cancel(5);
        System.out.println("cancel(5), seats = " + show.getSeatsAvailable());
        show.cancel(100);
        System.out.println("cancel(100), seats = " + show.getSeatsAvailable() + " (capped at capacity)");
        System.out.println("book(10): " + show.book(10) + ", seats = " + show.getSeatsAvailable());
        System.out.println("Total booked: " + getTotalBooked());
    }
}
