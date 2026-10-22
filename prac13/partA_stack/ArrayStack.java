import java.util.EmptyStackException;

public class ArrayStack<T> {
    private Object[] data = new Object[2];
    private int size = 0;

    public void push(T item) {
        if (size == data.length) data = java.util.Arrays.copyOf(data, size * 2);
        data[size++] = item;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (size == 0) throw new EmptyStackException();      // sensible handling of the empty case
        T item = (T) data[--size];
        data[size] = null;
        return item;
    }

    public boolean isEmpty() { return size == 0; }

    public static void main(String[] args) {
        ArrayStack<String> words = new ArrayStack<>();
        ArrayStack<Integer> nums = new ArrayStack<>();
        for (String w : new String[]{ "a", "b", "c" }) words.push(w);
        for (int i = 1; i <= 3; i++) nums.push(i * 10);
        while (!words.isEmpty()) System.out.print(words.pop() + " ");
        while (!nums.isEmpty()) System.out.print(nums.pop() + " ");
        System.out.println();
        try { words.pop(); } catch (EmptyStackException e) { System.out.println("Pop on empty stack -> EmptyStackException"); }
    }
}
