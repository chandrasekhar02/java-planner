package Week1.day7;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class GradeReportGenerator {
    public static void main(String[] args) {
        CsvProcessor csvProcessor = new CsvProcessor();
        HtmlProcessor htmlProcessor = new HtmlProcessor();

        CompletableFuture.supplyAsync(() -> {
            try {
                return csvProcessor.readStudents("students.csv");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        })
        .thenApply(students -> {
           
            return students.stream()
                    .sorted((s1, s2) -> Double.compare(s2.average(), s1.average()))
                    .toList();
        })
        .thenApply(sortedStudents -> htmlProcessor.generateHtml(sortedStudents))
        .thenAccept(html -> {
            try {
                htmlProcessor.writeHtml(html, "report.html");
                System.out.println("Report generated: report.html");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        })
        .join(); 
    }
}