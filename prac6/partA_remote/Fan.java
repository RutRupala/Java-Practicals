public class Fan implements Switchable {
    private boolean on;
    public void on() { on = true; System.out.println("Fan ON"); }
    public void off() { on = false; System.out.println("Fan OFF"); }
    public boolean isOn() { return on; }
}
