package Week1.Day3;

import java.util.*;

/**
 * Collections.sort() vs Arrays.sort()
 * 
 * Both sort objects but work with different data structures
 * and have different characteristics
 */
public class CollectionsSortVsArraysSort {

    static class Student implements Comparable<Student> {
        private String name;
        private int grade;

        public Student(String name, int grade) {
            this.name = name;
            this.grade = grade;
        }

        public String getName() {
            return name;
        }

        public int getGrade() {
            return grade;
        }

        @Override
        public int compareTo(Student other) {
            return Integer.compare(this.grade, other.grade);
        }

        @Override
        public String toString() {
            return String.format("%s (Grade: %d)", name, grade);
        }
    }

    public static void main(String[] args) {
        System.out.println(" Collectionsort\n");

        // Sample data
        Student[] studentArray = {
            new Student("Alice", 85),
            new Student("Bob", 92),
            new Student("Charlie", 78),
            new Student("Diana", 88),
            new Student("Eve", 95)
        };

        List<Student> studentList = Arrays.asList(
            new Student("Alice", 85),
            new Student("Bob", 92),
            new Student("Charlie", 78),
            new Student("Diana", 88),
            new Student("Eve", 95)
        );

        
        System.out.println(" Collections Works with Lists");
        System.out.println("Original list:");
        studentList.forEach(System.out::println);
        
        List<Student> listCopy = new ArrayList<>(studentList);
        Collections.sort(listCopy);
        System.out.println("\nAfter Collections.sort():");
        listCopy.forEach(System.out::println);
        System.out.println();

        
        System.out.println("2. Arrays.sort() - Works with Arrays");
        System.out.println("Original array:");
        for (Student s : studentArray) {
            System.out.println(s);
        }
        
        Student[] arrayCopy = Arrays.copyOf(studentArray, studentArray.length);
        Arrays.sort(arrayCopy);
        System.out.println("\nAfter Arrays.sort():");
        for (Student s : arrayCopy) {
            System.out.println(s);
        }
        System.out.println();

       
        System.out.println("3.Comparator");
        System.out.println("Sorted by name (descending):");
        List<Student> listByName = new ArrayList<>(studentList);
        Collections.sort(listByName, Collections.reverseOrder(
            Comparator.comparing(Student::getName)
        ));
        listByName.forEach(System.out::println);
        System.out.println();

       
        System.out.println("4. Arrays.sort() with Comparator");
        System.out.println("Sorted by name (descending):");
        Student[] arrayByName = Arrays.copyOf(studentArray, studentArray.length);
        Arrays.sort(arrayByName, Collections.reverseOrder(
            Comparator.comparing(Student::getName)
        ));
        for (Student s : arrayByName) {
            System.out.println(s);
        }
        System.out.println();

     
        System.out.println("5. List.sort() - Modern Java approach");
        System.out.println("Using default compareTo():");
        List<Student> modernList = new ArrayList<>(studentList);
        modernList.sort(null); // null means use Comparable
        modernList.forEach(System.out::println);
        System.out.println();

       
        System.out.println("\nConvert Array to List and sort:");
        List<Student> fromArray = Arrays.asList(studentArray);
        fromArray.sort(Comparator.comparingInt(Student::getGrade).reversed());
        System.out.println("Sorted by grade (descending):");
        fromArray.forEach(System.out::println);

       
        System.out.println("\nComplex: Sort by grade DESC, then by name ASC:");
        List<Student> complex = new ArrayList<>(studentList);
        complex.sort(
            Comparator.comparingInt(Student::getGrade).reversed()
                      .thenComparing(Student::getName)
        );
        complex.forEach(System.out::println);
    }
}
