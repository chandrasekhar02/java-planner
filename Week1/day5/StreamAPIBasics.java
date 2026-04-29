package Week1.day5;

import java.util.*;

public class StreamAPIBasics {

    static class Student {
        private String name;
        private int grade;
        private double gpa;

        public Student(String name, int grade, double gpa) {
            this.name = name;
            this.grade = grade;
            this.gpa = gpa;
        }

        public String getName() { return name; }
        public int getGrade() { return grade; }
        public double getGpa() { return gpa; }

        @Override
        public String toString() {
            return String.format("%s (Grade %d, GPA: %.2f)", name, grade, gpa);
        }
    }

    static class FilterExamples {
        public static void demonstrate() {
            System.out.println(" FILTER\n");

            List<Student> students = Arrays.asList(
                new Student("Alice", 10, 3.8),
                new Student("Bob", 9, 3.2),
                new Student("Charlie", 10, 3.9),
                new Student("Diana", 8, 3.5),
                new Student("Eve", 10, 3.6)
            );

            System.out.println("1. Students in Grade 10:");
            students.stream()
                .filter(s -> s.getGrade() == 10)
                .forEach(System.out::println);

            System.out.println("\n2. Students with GPA > 3.5:");
            students.stream()
                .filter(s -> s.getGpa() > 3.5)
                .forEach(System.out::println);

            System.out.println("\n3. Grade 10 AND GPA > 3.7:");
            students.stream()
                .filter(s -> s.getGrade() == 10)
                .filter(s -> s.getGpa() > 3.7)
                .forEach(System.out::println);

            System.out.println("\n4. NOT in Grade 9:");
            students.stream()
                .filter(s -> s.getGrade() != 9)
                .forEach(System.out::println);
        }
    }

    static class MapExamples {
        public static void demonstrate() {
            System.out.println("\n MAP\n");

            List<Student> students = Arrays.asList(
                new Student("Alice", 10, 3.8),
                new Student("Bob", 9, 3.2),
                new Student("Charlie", 10, 3.9)
            );

            System.out.println("1. Extract all names:");
            students.stream()
                .map(Student::getName)
                .forEach(System.out::println);

            System.out.println("\n2. Extract all GPAs:");
            students.stream()
                .map(Student::getGpa)
                .forEach(gpa -> System.out.printf("%.2f%n", gpa));

            System.out.println("\n3. Custom format:");
            students.stream()
                .map(s -> s.getName() + " (GPA: " + s.getGpa() + ")")
                .forEach(System.out::println);

            System.out.println("\n4. Uppercase names with GPA:");
            students.stream()
                .map(Student::getName)
                .map(String::toUpperCase)
                .forEach(System.out::println);

            System.out.println("\n5. Names of Grade 10 students:");
            students.stream()
                .filter(s -> s.getGrade() == 10)
                .map(Student::getName)
                .forEach(System.out::println);
        }
    }

    static class FlatMapExamples {
        public static void demonstrate() {
            System.out.println("\nFLATMAP\n");

            System.out.println("1. Flatten list of lists:");
            List<List<Integer>> nestedNumbers = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5),
                Arrays.asList(6, 7, 8, 9)
            );

            nestedNumbers.stream()
                .flatMap(List::stream)
                .forEach(n -> System.out.print(n + " "));
            System.out.println();

            System.out.println("\n2. Flatten words to unique characters:");
            List<String> words = Arrays.asList("hello", "world");
            words.stream()
                .flatMap(word -> word.chars().boxed().map(c -> (char) c.intValue()).map(String::valueOf))
                .distinct()
                .forEach(System.out::print);
            System.out.println();

            System.out.println("\n3. Generate multiples (each number * 1,2,3):");
            List<Integer> numbers = Arrays.asList(1, 2, 3);
            numbers.stream()
                .flatMap(n -> Arrays.asList(n, n*2, n*3).stream())
                .forEach(n -> System.out.print(n + " "));
            System.out.println();

            System.out.println("\n4. Split sentences into words:");
            List<String> sentences = Arrays.asList(
                "Hello world",
                "Stream API",
                "Java programming"
            );
            sentences.stream()
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .forEach(w -> System.out.print(w + " "));
            System.out.println();
        }
    }

    static class CollectExamples {
        public static void demonstrate() {
            System.out.println("\n COLLECT\n");

            List<Student> students = Arrays.asList(
                new Student("Alice", 10, 3.8),
                new Student("Bob", 9, 3.2),
                new Student("Charlie", 10, 3.9),
                new Student("Diana", 8, 3.5)
            );

            System.out.println("1. Collect filtered students to List:");
            List<Student> topStudents = students.stream()
                .filter(s -> s.getGpa() > 3.5)
                .collect(ArrayList::new, List::add, List::addAll);
            topStudents.forEach(System.out::println);

            System.out.println("\n2. Collect grades to Set:");
            Set<Integer> grades = students.stream()
                .map(Student::getGrade)
                .collect(HashSet::new, Set::add, Set::addAll);
            System.out.println("Unique grades: " + grades);

            System.out.println("\n3. Collect names to Set:");
            Set<String> names = students.stream()
                .map(Student::getName)
                .collect(HashSet::new, Set::add, Set::addAll);
            System.out.println("Names: " + names);

            System.out.println("\n4. Count students:");
            long count = students.stream()
                .filter(s -> s.getGrade() == 10)
                .collect(java.util.stream.Collectors.counting());
            System.out.println("Grade 10 students: " + count);
        }
    }

    static class CombinedExamples {
        public static void demonstrate() {
            System.out.println("\nCOMBINED\n");

            List<Student> students = Arrays.asList(
                new Student("Alice", 10, 3.8),
                new Student("Bob", 9, 3.2),
                new Student("Charlie", 10, 3.9),
                new Student("Diana", 8, 3.5),
                new Student("Eve", 10, 3.6)
            );

            System.out.println("1. Get names of Grade 10 students:");
            List<String> gradeNames = students.stream()
                .filter(s -> s.getGrade() == 10)
                .map(Student::getName)
                .collect(ArrayList::new, List::add, List::addAll);
            System.out.println(gradeNames);

            System.out.println("\n2. Pipeline: filter -> map -> collect:");
            List<String> topNames = students.stream()
                .filter(s -> s.getGpa() > 3.5)
                .map(s -> "★ " + s.getName())
                .collect(ArrayList::new, List::add, List::addAll);
            topNames.forEach(System.out::println);

            System.out.println("\n3. Complex: Extract grades of top GPA students:");
            Set<Integer> topGrades = students.stream()
                .filter(s -> s.getGpa() >= 3.8)
                .map(Student::getGrade)
                .collect(HashSet::new, Set::add, Set::addAll);
            System.out.println("Grades: " + topGrades);
        }
    }

    public static void main(String[] args) {
        System.out.println("STREAM API BASICS \n");

        FilterExamples.demonstrate();
        MapExamples.demonstrate();
        FlatMapExamples.demonstrate();
        CollectExamples.demonstrate();
        CombinedExamples.demonstrate();

    }
}
