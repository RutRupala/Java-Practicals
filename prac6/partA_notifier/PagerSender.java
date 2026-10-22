public class PagerSender implements Notifier, Urgent {
    public void send(String message) { System.out.println("[PAGER] " + message); }
}
