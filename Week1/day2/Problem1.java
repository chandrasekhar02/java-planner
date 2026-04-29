package Week1.day2;

import java.util.*;

/**
 * Problem 1: Student Grade Management System
 *
 * Scenario: You are building a system to manage student grades for a class.
 * Requirements:
 * - Add grades for students
 * - Remove a specific grade
 * - Check if a grade exists
 * - Display all grades in order
 * - Calculate average grade
 *
 * This problem demonstrates List collections (ArrayList and LinkedList).
 * ArrayList is used for fast random access and when insertions/deletions are rare.
 * LinkedList is used when frequent insertions/deletions occur at the beginning or middle.
 */
public class Problem1 {
    public static void main(String[] args) {
      
        System.out.println(" Using ArrayList ");
        List<Double> gradesArrayList = new ArrayList<>();
        gradesArrayList.add(85.5);
        gradesArrayList.add(92.0);
        gradesArrayList.add(78.3);
        gradesArrayList.add(88.7);

        gradesArrayList.remove(78.3);

        System.out.println("Contains 92.0: " + gradesArrayList.contains(92.0));

        System.out.println("Grades:");
        for (double grade : gradesArrayList) {
            System.out.println(grade);
        }

        double sum = 0;
        for (double grade : gradesArrayList) {
            sum += grade;
        }
        System.out.println("Average: " + (sum / gradesArrayList.size()));

        System.out.println("\nUsing LinkedList ");
 
        LinkedList<Double> gradesLinkedList = new LinkedList<>();
        gradesLinkedList.add(85.5);
        gradesLinkedList.add(92.0);
        gradesLinkedList.add(78.3);
        gradesLinkedList.add(88.7);

     
        gradesLinkedList.addFirst(95.0);
        gradesLinkedList.addLast(76.5);

        gradesLinkedList.remove(2); // Remove 92.0

       
        System.out.println("Grades:");
        Iterator<Double> iterator = gradesLinkedList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        sum = 0;
        iterator = gradesLinkedList.iterator();
        while (iterator.hasNext()) {
            sum += iterator.next();
        }
        System.out.println("Average: " + (sum / gradesLinkedList.size()));
    }
}