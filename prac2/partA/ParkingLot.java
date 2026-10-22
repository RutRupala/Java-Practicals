public class ParkingLot {
    private int twoWheelers, fourWheelers;
    private final int twoCap, fourCap;
    private static long revenue = 0;

    public ParkingLot(int twoCap, int fourCap) { this.twoCap = twoCap; this.fourCap = fourCap; }

    public void park(String type) {
        if (type.equals("two")) {
            if (twoWheelers < twoCap) { twoWheelers++; revenue += 20; System.out.println("Parked two-wheeler"); }
            else System.out.println("Full (two-wheeler) - rejected");
        } else if (type.equals("four")) {
            if (fourWheelers < fourCap) { fourWheelers++; revenue += 40; System.out.println("Parked four-wheeler"); }
            else System.out.println("Full (four-wheeler) - rejected");
        } else System.out.println("Unknown type: " + type);
    }

    public void leave(String type) {
        if (type.equals("two")) twoWheelers = Math.max(0, twoWheelers - 1);
        else if (type.equals("four")) fourWheelers = Math.max(0, fourWheelers - 1);
        System.out.println(type + "-wheeler left");
    }

    public static void main(String[] args) {
        ParkingLot lot = new ParkingLot(2, 1);
        lot.park("two"); lot.park("two"); lot.park("two");
        lot.park("four"); lot.park("four");
        lot.leave("two"); lot.park("two");
        lot.leave("four"); lot.leave("four");
        System.out.println("Occupancy: two=" + lot.twoWheelers + ", four=" + lot.fourWheelers);
        System.out.println("Revenue: " + revenue);
    }
}
