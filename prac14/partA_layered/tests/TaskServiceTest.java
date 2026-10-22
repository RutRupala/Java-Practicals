// JUnit 5 tests. Compile/run with the JUnit console launcher or from an IDE (VS Code / IntelliJ / Codespaces).
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskServiceTest {
    @Test
    void addingIncreasesCount() {
        TaskService s = new TaskService();
        s.add("Write report");
        assertEquals(1, s.count());
    }

    @Test
    void completingMarksTaskDone() {
        TaskService s = new TaskService();
        Task t = s.add("Lab 14");
        s.complete(t.id());
        assertTrue(s.list().get(0).done());
    }

    @Test
    void invalidActionThrows() {
        TaskService s = new TaskService();
        assertThrows(IllegalArgumentException.class, () -> s.add("   "));
        assertThrows(IllegalArgumentException.class, () -> s.complete(99));
    }
}
