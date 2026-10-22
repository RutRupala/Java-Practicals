import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String name;
    private final int marks;
    private transient String password;      // transient -> NOT saved

    Student(String name, int marks, String password) { this.name = name; this.marks = marks; this.password = password; }
    @Override public String toString() { return name + " (" + marks + ") password=" + password; }
}
