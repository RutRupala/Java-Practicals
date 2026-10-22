import java.util.Scanner;

public class TollBooth {
    record Vehicle(String number, String type) { }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int totalToll = 0, bike = 0, car = 0, truck = 0;
        while (true) {
            System.out.print("Vehicle number (or 'done'): ");
            String number = sc.next();
            if (number.equalsIgnoreCase("done")) break;
            System.out.print("Type (bike/car/truck): ");
            Vehicle v = new Vehicle(number, sc.next().toLowerCase());
            int toll;
            switch (v.type()) {
                case "bike" -> { toll = 20; bike++; }
                case "car" -> { toll = 50; car++; }
                case "truck" -> { toll = 150; truck++; }
                default -> { System.out.println("Unknown type, skipped."); continue; }
            }
            totalToll += toll;
            System.out.println(v + " toll = " + toll);
        }
        String most = "bike";
        int max = bike;
        if (car > max) { most = "car"; max = car; }
        if (truck > max) { most = "truck"; max = truck; }
        System.out.println("Total toll: " + totalToll);
        System.out.println("Most frequent: " + most);
    }
}
