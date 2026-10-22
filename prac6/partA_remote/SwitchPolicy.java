@FunctionalInterface
public interface SwitchPolicy {
    boolean mayTurnOn(Switchable device, int hour);
}
