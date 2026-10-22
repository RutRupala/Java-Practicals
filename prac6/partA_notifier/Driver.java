public class Driver {
    public static void main(String[] args) {
        Notifier email = msg -> System.out.println("[EMAIL] " + msg);
        Notifier sms = msg -> System.out.println("[SMS] " + msg);
        Notifier[] senders = { email, sms, new PagerSender() };
        String message = "Server down!";
        for (Notifier n : senders) {
            n.send(message);
            if (n instanceof Urgent) n.send(message);      // urgent senders send twice
        }
    }
}
