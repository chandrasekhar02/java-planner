package Week1.day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class CsvProcessor extends Processor {
    public List<StudentRecord> readStudents(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        return lines.stream()
                .skip(1) // skip header
                .map(line -> {
                    String[] parts = line.split(",");
                    String name = parts[0];
                    List<Integer> grades = Arrays.stream(parts).skip(1)
                            .map(Integer::parseInt)
                            .collect(Collectors.toList());
                    return new StudentRecord(name, grades);
                })
                .collect(Collectors.toList());
    }
}