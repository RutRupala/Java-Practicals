import java.util.ArrayList;
import java.util.List;

public class TaskService {
    private final List<Task> tasks = new ArrayList<>();
    private int nextId = 1;

    public Task add(String title) {
        if (title == null || title.isBlank()) throw new IllegalArgumentException("Title must not be blank");
        Task t = new Task(nextId++, title.trim(), false);
        tasks.add(t);
        return t;
    }

    public void complete(int id) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).id() == id) { tasks.set(i, tasks.get(i).complete()); return; }
        }
        throw new IllegalArgumentException("No task with id " + id);
    }

    public List<Task> list() { return new ArrayList<>(tasks); }
    public int count() { return tasks.size(); }
}
