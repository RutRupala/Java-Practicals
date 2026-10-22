/** Same checks as TaskServiceTest, but runnable WITHOUT JUnit (plain Java): javac -cp .. then java -cp .. */
public class TaskServiceChecks {
    static int failed = 0;
    static void check(String name, boolean ok) { System.out.println((ok ? "PASS " : "FAIL ") + name); if (!ok) failed++; }

    public static void main(String[] args) {
        TaskService s = new TaskService();
        s.add("Write report");
        check("addingIncreasesCount", s.count() == 1);
        s.complete(1);
        check("completingMarksTaskDone", s.list().get(0).done());
        boolean threw1 = false, threw2 = false;
        try { s.add("  "); } catch (IllegalArgumentException e) { threw1 = true; }
        try { s.complete(99); } catch (IllegalArgumentException e) { threw2 = true; }
        check("invalidActionThrows", threw1 && threw2);
        if (failed > 0) System.exit(1);
    }
}
