public class Demo {
    static <T> T first(T[] arr) { return arr[0]; }

    public static void main(String[] args) {
        Box<String> s = new Box<>("hello");
        Box<Integer> n = new Box<>(42);
        String str = s.get();            // no cast needed
        int num = n.get();
        // s.set(10);                    // compile error: type safety
        Pair<String, Integer> p = new Pair<>("Riya", 19);
        System.out.println(str + " " + num + " " + p);
        System.out.println("first = " + first(new String[]{ "a", "b" }) + ", " + first(new Integer[]{ 7, 8 }));
    }
}
