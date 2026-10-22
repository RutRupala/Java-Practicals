public record Task(int id, String title, boolean done) {
    Task complete() { return new Task(id, title, true); }
}
