package Week1.CODE.amitsirproblem;

import java.util.*;

class Student {
    int roll;
    String name;
    boolean present;

    Student(int roll, String name) {
        this.roll = roll;
        this.name = name;
        this.present = false;
    }
    
    public String toString() {
        return "Roll: " + roll + " | Name: " + name + " | " + (present ? "PRESENT" : "ABSENT");
    }
}

class AttendanceTracker {
    ArrayList<Student> students = new ArrayList<>();

    void addStudent(int roll, String name) {
        for(Student s : students) {
            if(s.roll == roll) {
                System.out.println("Error: Roll " + roll + " exists");
                return;
            }
        }
        students.add(new Student(roll, name));
        System.out.println("Added: " + name);
    }

    void markPresent(int roll) {
        for(Student s : students) {
            if(s.roll == roll) {
                s.present = true;
                System.out.println(s.name + " marked present");
                return;
            }
        }
        System.out.println("Roll " + roll + " not found");
    }

    Student search(int roll) {
        for(Student s : students)
            if(s.roll == roll) return s;
        return null;
    }

    void printAll() {
        System.out.println("\nAll Students:");
        for(Student s : students) System.out.println("  " + s);
    }

    void printPresent() {
        ArrayList<Student> present = new ArrayList<>();
        for(Student s : students)
            if(s.present) present.add(s);

        if(present.isEmpty()) {
            System.out.println("\nNo present students");
            return;
        }

        present.sort((a, b) -> a.name.compareTo(b.name));
        System.out.println("\n prsnt stdnst sorted :");
        for(Student s : present)
            System.out.println("  " + s.name + " (Roll " + s.roll + ")");
    }
}

public class Main {
    public static void main(String[] args) {
        AttendanceTracker t = new AttendanceTracker();

        System.out.println("Adding students:");
        t.addStudent(101, "ritu");
        t.addStudent(102, "kabya");
        t.addStudent(103, "Chandrasekhar");
        t.addStudent(104, "dhiraj");
        t.addStudent(105, "AMitsir");

        t.addStudent(101, "Saranga");

        System.out.println("\nMarking attendance:");
        t.markPresent(101);
        t.markPresent(103);
        t.markPresent(104);
        t.markPresent(999);

        t.printAll();
        t.printPresent();

        System.out.println("\n Search roll :");
        Student found = t.search(103);
        if(found != null) System.out.println("Found: " + found);
        else System.out.println("Roll  not found");
    }
}
