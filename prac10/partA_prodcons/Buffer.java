public class Buffer {
    private final int[] items = new int[3];
    private int count = 0, in = 0, out = 0;

    public synchronized void put(int v) throws InterruptedException {
        while (count == items.length) wait();          // buffer full -> wait
        items[in] = v; in = (in + 1) % items.length; count++;
        notifyAll();
    }

    public synchronized int take() throws InterruptedException {
        while (count == 0) wait();                     // buffer empty -> wait
        int v = items[out]; out = (out + 1) % items.length; count--;
        notifyAll();
        return v;
    }
}
