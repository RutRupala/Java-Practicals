public class Driver {
    public static void main(String[] args) {
        Point[] pts = { new Point(1, 2), new Point(3, 4), new Point(1, 2), new Point(5, 6), new Point(3, 4) };
        int distinct = 0;
        for (int i = 0; i < pts.length; i++) {
            boolean seen = false;
            for (int j = 0; j < i; j++) {
                if (pts[i].equals(pts[j])) { seen = true; break; }
            }
            if (!seen) distinct++;
        }
        System.out.println("Points: " + java.util.Arrays.toString(pts));
        System.out.println("Distinct: " + distinct);
    }
}
