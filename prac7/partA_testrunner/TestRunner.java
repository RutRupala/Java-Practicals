import java.lang.reflect.Method;

public class TestRunner {
    public static void main(String[] args) throws Exception {
        Object target = new SampleTests();
        int ran = 0, failed = 0;
        for (Method m : target.getClass().getDeclaredMethods()) {
            if (!m.isAnnotationPresent(Run.class)) continue;
            ran++;
            try {
                m.invoke(target);
            } catch (java.lang.reflect.InvocationTargetException e) {
                failed++;
                System.out.println("  " + m.getName() + " FAILED: " + e.getCause().getMessage());
            }
        }
        System.out.println(ran + " @Run methods executed, " + failed + " failed");
    }
}
