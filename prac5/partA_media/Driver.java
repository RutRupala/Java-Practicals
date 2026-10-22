public class Driver {
    public static void main(String[] args) {
        Media[] returned = { new Book("Java Basics"), new Dvd("Inception"), new Magazine("Tech Today") };
        int[] daysLate = { 3, 9, 20 };
        double total = 0;
        for (int i = 0; i < returned.length; i++) {
            double fee = returned[i].lateFee(daysLate[i]);
            total += fee;
            System.out.printf("%-12s (%s) %2d days late -> fee %.2f%n",
                    returned[i].title, returned[i].getClass().getSimpleName(), daysLate[i], fee);
        }
        System.out.printf("Total late fees: %.2f%n", total);
    }
}
