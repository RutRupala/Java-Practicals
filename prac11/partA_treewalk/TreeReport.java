import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class TreeReport {
    public static void main(String[] args) throws IOException {
        Path root = Paths.get(args.length > 0 ? args[0] : ".");
        List<String> report = new ArrayList<>();
        report.add("name | size(bytes) | last modified");
        Files.walkFileTree(root, new SimpleFileVisitor<Path>() {
            @Override public FileVisitResult visitFile(Path file, BasicFileAttributes a) {
                report.add(file.getFileName() + " | " + a.size() + " | " + a.lastModifiedTime());
                return FileVisitResult.CONTINUE;
            }
        });
        report.add("Total files: " + (report.size() - 1));
        Path out = Paths.get("tree-report.txt");
        Files.write(out, report);
        report.forEach(System.out::println);
        System.out.println("Report written to " + out.toAbsolutePath());
    }
}
