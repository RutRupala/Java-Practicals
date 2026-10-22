import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class LogAnalyzer {
    public static void main(String[] args) throws IOException {
        Path dir = Files.createTempDirectory("loganalyzer");
        Files.write(dir.resolve("a.log"), List.of("INFO start", "ERROR disk full", "INFO ok"));
        Files.write(dir.resolve("b.log"), List.of("ERROR timeout", "ERROR timeout again"));
        Files.write(dir.resolve("c.log"), List.of("INFO nothing here"));

        String keyword = "ERROR";
        int totalLines = 0, matches = 0;
        try (DirectoryStream<Path> ds = Files.newDirectoryStream(dir, "*.log")) {
            for (Path p : ds) {
                List<String> lines = Files.readAllLines(p);
                totalLines += lines.size();
                for (String l : lines) if (l.contains(keyword)) matches++;
                System.out.println(p.getFileName() + " : size = " + Files.size(p) + " bytes, lines = " + lines.size());
            }
        }
        System.out.println("Total lines: " + totalLines + ", lines containing " + keyword + ": " + matches);
    }
}
