public class Driver {
    public static void main(String[] args) {
        Switchable[] devices = { new Fan(), new Light() };
        for (Switchable d : devices) d.toggle();
        for (Switchable d : devices) d.toggle();

        // 1) anonymous class
        SwitchPolicy anon = new SwitchPolicy() {
            @Override public boolean mayTurnOn(Switchable device, int hour) {
                return hour >= 6 && hour < 23;
            }
        };
        // 2) lambda doing the same job
        SwitchPolicy lambda = (device, hour) -> hour >= 6 && hour < 23;

        for (int hour : new int[]{ 3, 12, 23 }) {
            System.out.println("hour " + hour + ": anonymous=" + anon.mayTurnOn(devices[0], hour)
                    + ", lambda=" + lambda.mayTurnOn(devices[0], hour));
        }
    }
}
