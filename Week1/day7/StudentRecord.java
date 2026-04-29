package Week1.day7;

import java.util.List;

public record StudentRecord(String name, List<Integer> grades) {
    public double average() {
        return grades.stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }

    public String grade() {
        double avg = average();
        if (avg >= 90) return "A";
        else if (avg >= 80) return "B";
        else if (avg >= 70) return "C";
        else if (avg >= 60) return "D";
        else return "F";
    }
}