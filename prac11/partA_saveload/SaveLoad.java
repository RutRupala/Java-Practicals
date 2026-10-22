import java.io.*;

public class SaveLoad {
    public static void main(String[] args) throws Exception {
        Student[] students = { new Student("Riya", 88, "secret1"), new Student("Arjun", 75, "secret2") };
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("students.dat"))) {
            out.writeObject(students);
        }
        Student[] loaded;
    
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("students.dat"))) {
            loaded = (Student[]) in.readObject();
        }
        System.out.println("Saved : " + java.util.Arrays.toString(students));
        System.out.println("Loaded: " + java.util.Arrays.toString(loaded) + "   <- transient password is null");
    }
}
