package Week1.day2;

import java.util.*;

/**
 * Problem 3: Student Grade Lookup System
 *
 * Scenario: You need to create a system to store and lookup student grades by student name.
 * Requirements:
 * - Store student name and grade pairs
 * - Lookup grade by student name
 * - Update a student's grade
 * - Display all students and grades in different orders
 *
 * This problem demonstrates Map collections (HashMap, TreeMap, LinkedHashMap).
 * HashMap: Fast lookup, no order guarantee
 * TreeMap: Sorted by key, slower operations
 * LinkedHashMap: Insertion order maintained
 */
public class Problem3 {
    public static void main(String[] args) {
        System.out.println("no order");
        Map<String, Double> gradesHashMap = new HashMap<>();
        gradesHashMap.put("Alice", 85.5);
        gradesHashMap.put("Bob", 92.0);
        gradesHashMap.put("Charlie", 78.3);
        gradesHashMap.put("Diana", 88.7);

        System.out.println("Alice's grade: " + gradesHashMap.get("Alice"));
        gradesHashMap.put("Alice", 90.0); // Update grade

        System.out.println("All grades:");
        for (Map.Entry<String, Double> entry : gradesHashMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("\n Using TreeMap");
        Map<String, Double> gradesTreeMap = new TreeMap<>();
        gradesTreeMap.put("Alice", 85.5);
        gradesTreeMap.put("Bob", 92.0);
        gradesTreeMap.put("Charlie", 78.3);
        gradesTreeMap.put("Diana", 88.7);

        System.out.println("Alice's grade: " + gradesTreeMap.get("Alice"));
        gradesTreeMap.put("Alice", 90.0);

        System.out.println("All grades (sorted by name):");
        Iterator<Map.Entry<String, Double>> iterator = gradesTreeMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Double> entry = iterator.next();
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("\n Using LinkedHashMap (insertion order) ");
        Map<String, Double> gradesLinkedHashMap = new LinkedHashMap<>();
        gradesLinkedHashMap.put("Alice", 85.5);
        gradesLinkedHashMap.put("Bob", 92.0);
        gradesLinkedHashMap.put("Charlie", 78.3);
        gradesLinkedHashMap.put("Diana", 88.7);

        System.out.println("Alice's grade: " + gradesLinkedHashMap.get("Alice"));
        gradesLinkedHashMap.put("Alice", 90.0);

        System.out.println("All grades (insertion order):");
        for (Map.Entry<String, Double> entry : gradesLinkedHashMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}