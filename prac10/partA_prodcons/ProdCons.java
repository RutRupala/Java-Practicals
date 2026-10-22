public class ProdCons {
    public static void main(String[] args) throws InterruptedException {
        Buffer buf = new Buffer();
        final int N = 10;
        Thread producer = new Thread(() -> {
            try { for (int i = 1; i <= N; i++) { buf.put(i); System.out.println("Produced " + i); } }
            catch (InterruptedException e) { }
        });
        Thread consumer = new Thread(() -> {
            try { for (int i = 1; i <= N; i++) { System.out.println("   Consumed " + buf.take()); } }
            catch (InterruptedException e) { }
        });
        producer.start(); consumer.start();
        producer.join(); consumer.join();
        System.out.println("Produced and consumed " + N + " items in order, none lost");
    }
}
