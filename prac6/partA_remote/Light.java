public class Light implements Switchable {
    private boolean on;
    public void on() { on = true; System.out.println("Light ON"); }
    public void off() { on = false; System.out.println("Light OFF"); }
    public boolean isOn() { return on; }
}
