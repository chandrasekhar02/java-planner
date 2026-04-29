package Week1.day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public final class HtmlProcessor extends Processor {
    public String generateHtml(List<StudentRecord> students) {
        StringBuilder rows = new StringBuilder();
        for (StudentRecord student : students) {
            rows.append("""
                <tr>
                    <td>%s</td>
                    <td>%.2f</td>
                    <td>%s</td>
                </tr>
                """.formatted(student.name(), student.average(), student.grade()));
        }

        String html = """
            <!DOCTYPE html>
            <html>
            <head>
                <title>Student Grade Report</title>
                <style>
                    table { border-collapse: collapse; width: 100%; }
                    th, td { border: 1px solid black; padding: 8px; text-align: left; }
                    th { background-color: #f2f2f2; }
                </style>
            </head>
            <body>
                <h1>Student Grade Report</h1>
                <table>
                    <tr>
                        <th>Name</th>
                        <th>Average</th>
                        <th>Grade</th>
                    </tr>
                    %s
                </table>
            </body>
            </html>
            """.formatted(rows.toString());

        return html;
    }

    public void writeHtml(String html, String filePath) throws IOException {
        Files.writeString(Paths.get(filePath), html);
    }
}