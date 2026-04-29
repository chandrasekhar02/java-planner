package Week1.day2;

import java.util.*;

/**
 * Problem 4: Task Management with Different Data Structures
 *
 * Scenario: You are building a task management system that needs to handle tasks
 * in different ways: as a queue (FIFO), stack (LIFO), and linked list for flexibility.
 * Requirements:
 * - Add tasks to be processed
 * - Process tasks in FIFO order (Queue)
 * - Process tasks in LIFO order (Stack)
 * - Insert/remove tasks at specific positions (LinkedList)
 *
 * This problem demonstrates Queue, Stack, and LinkedList usage.
 */
public class Problem4 {
    public static void main(String[] args) {
        System.out.println(" Using Queue ");
        Queue<String> taskQueue = new LinkedList<>();
        taskQueue.add("Task 1: Review code");
        taskQueue.add("Task 2: Write tests");
        taskQueue.add("Task 3: Deploy application");

        System.out.println("Processing tasks in FIFO order:");
        while (!taskQueue.isEmpty()) {
            System.out.println("Processing: " + taskQueue.poll());
        }

        System.out.println("\nUsing Stack ");
        Stack<String> taskStack = new Stack<>();
        taskStack.push("Task 1: Review code");
        taskStack.push("Task 2: Write tests");
        taskStack.push("Task 3: Deploy application");

        System.out.println("Processing tasks in LIFO order:");
        while (!taskStack.isEmpty()) {
            System.out.println("Processing: " + taskStack.pop());
        }

        System.out.println("\nUsing LinkedList ");
        LinkedList<String> taskList = new LinkedList<>();
        taskList.add("Task 1: Review code");
        taskList.add("Task 2: Write tests");
        taskList.add("Task 3: Deploy application");

        // Insert at specific position
        taskList.add(1, "Task 1.5: Code formatting");

        // Remove from specific position
        taskList.remove(2);

        System.out.println("Tasks after modifications:");
        Iterator<String> iterator = taskList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // Demonstrate ListIterator for bidirectional traversal
        System.out.println("\nBidirectional traversal:");
        ListIterator<String> listIterator = taskList.listIterator();
        System.out.println("Forward:");
        while (listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }
        System.out.println("Backward:");
        while (listIterator.hasPrevious()) {
            System.out.println(listIterator.previous());
        }
    }
}