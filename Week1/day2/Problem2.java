package Week1.day2;

import java.util.*;

/**
 * Problem 2: Unique Student ID Management
 *
 * Scenario: You need to manage a set of unique student IDs for a university system.
 * Requirements:
 * - Add student IDs
 * - Remove a student ID
 * - Check if a student ID exists
 * - Display all student IDs in different orders
 *
 * This problem demonstrates Set collections (HashSet, TreeSet, LinkedHashSet).
 * HashSet: Fast lookup, no order guarantee
 * TreeSet: Sorted order, slower operations
 * LinkedHashSet: Insertion order maintained
 */
public class Problem2 {
    public static void main(String[] args) {
        System.out.println("Using HashSet ");
        Set<String> studentIdsHashSet = new HashSet<>();
        studentIdsHashSet.add("S001");
        studentIdsHashSet.add("S003");
        studentIdsHashSet.add("S002");
        studentIdsHashSet.add("S001"); 
        System.out.println("Contains S002: " + studentIdsHashSet.contains("S002"));
        studentIdsHashSet.remove("S003");

        System.out.println("Student IDs:");
        for (String id : studentIdsHashSet) {
            System.out.println(id);
        }

        System.out.println("\nUsing TreeSet sorted order");
        Set<String> studentIdsTreeSet = new TreeSet<>();
        studentIdsTreeSet.add("S001");
        studentIdsTreeSet.add("S003");
        studentIdsTreeSet.add("S002");
        studentIdsTreeSet.add("S001"); 

        System.out.println("Contains S002: " + studentIdsTreeSet.contains("S002"));
        studentIdsTreeSet.remove("S003");

        System.out.println("Student IDs (sorted):");
        Iterator<String> iterator = studentIdsTreeSet.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("\ninsertion order");
        Set<String> studentIdsLinkedHashSet = new LinkedHashSet<>();
        studentIdsLinkedHashSet.add("S001");
        studentIdsLinkedHashSet.add("S003");
        studentIdsLinkedHashSet.add("S002");
        studentIdsLinkedHashSet.add("S001"); 

        System.out.println("Contains S002: " + studentIdsLinkedHashSet.contains("S002"));
        studentIdsLinkedHashSet.remove("S003");

        System.out.println("Student IDs (insertion order):");
        for (String id : studentIdsLinkedHashSet) {
            System.out.println(id);
        }
    }
}