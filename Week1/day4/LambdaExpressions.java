package Week1.day4;

import java.util.*;

public class LambdaExpressions {

    
    static class SyntaxExamples {
        
        public static void demonstrateSyntax() {
            System.out.println("1. NO PARAMETERS:");
            Runnable r1 = () -> System.out.println("Hello from lambda!");
            r1.run();
            
            System.out.println("\n2. SINGLE PARAMETER (parentheses optional):");
            List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
            names.forEach(name -> System.out.println("Name: " + name));
            
            System.out.println("\n3. MULTIPLE PARAMETERS:");
            Calculator add = (a, b) -> a + b;
            System.out.println("10 + 5 = " + add.calculate(10, 5));
            
            System.out.println("\n4. EXPLICIT TYPES:");
            Calculator multiply = (int x, int y) -> x * y;
            System.out.println("10 * 5 = " + multiply.calculate(10, 5));
            
            System.out.println("\n5. MULTI-LINE BODY:");
            Calculator divide = (x, y) -> {
                if (y == 0) {
                    return 0;
                }
                return x / y;
            };
            System.out.println("10 / 5 = " + divide.calculate(10, 5));
            System.out.println("10 / 0 = " + divide.calculate(10, 0));
        }
    }

    
    static class VariableCapture {
        
        public static void demonstrateCapture() {
            System.out.println("\n===== VARIABLE CAPTURE =====\n");
            
            System.out.println("1. FINAL VARIABLES:");
            final int multiplier = 10;
            List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
            
            numbers.forEach(n -> System.out.print(n * multiplier + " "));
            System.out.println();
            
            System.out.println("\n2. EFFECTIVELY FINAL (never modified):");
            int threshold = 3;
            
            List<Integer> filtered = new ArrayList<>();
            numbers.forEach(n -> {
                if (n > threshold) {
                    filtered.add(n);
                }
            });
            System.out.println("Values > " + threshold + ": " + filtered);
            
            System.out.println("\n3. CAPTURING 'this' (implicit):");
            Counter counter = new Counter(0);
            counter.demonstrateThisCapture();
            
            System.out.println("\n4. MODIFICATION ERROR (uncommenting causes compile error):");
            int value = 5;
            System.out.println("The variable 'value' cannot be changed inside the lambda.");
        }
    }

    static class Counter {
        private int count;

        public Counter(int count) {
            this.count = count;
        }

        public void demonstrateThisCapture() {
            List<Integer> numbers = Arrays.asList(1, 2, 3);
            
            numbers.forEach(n -> {
                this.count += n;
            });
            
            System.out.println("Sum: " + this.count);
        }
    }

    
    static class VariableScope {
        
        public static void demonstrateScope() {
            System.out.println("\n===== VARIABLE SCOPE =====\n");
            
            int outerVar = 100;
            
            Runnable r = () -> System.out.println("Outer var: " + outerVar);
            r.run();
            
            List<String> items = Arrays.asList("A", "B", "C");
            items.forEach(item -> {
                String processed = item.toLowerCase();
                System.out.println("Item: " + processed);
            });
            
            System.out.println("\nVariable shadowing:");
            Integer x = 10;
            items.forEach(item -> {
                Integer x_local = 20;
                System.out.println("Outer x=" + x + ", Local x_local=" + x_local);
            });
        }
    }

    
    static class ClosureBehavior {
        
        public static void demonstrateClosures() {
            System.out.println("\n CLOSURE BEHAVIOR \n");
            
            System.out.println("1. CAPTURING PRIMITIVE VALUES:");
            int x = 5;
            int y = 10;
            
            Calculator add = (a, b) -> a + b + x + y;
            System.out.println("Lambda uses captured values: " + add.calculate(1, 2));
            
            System.out.println("\n2. CAPTURING MUTABLE OBJECTS ");
            List<String> list = new ArrayList<>(Arrays.asList("Item1"));
            
            Runnable addItem = () -> {
                list.add("Item2");
                System.out.println("List after lambda: " + list);
            };
            
            addItem.run();
            System.out.println("List after execution: " + list);
        }
    }

    
    @FunctionalInterface
    interface Calculator {
        int calculate(int a, int b);
    }

    
    public static void main(String[] args) {
        System.out.println(" LAMBDA EXPRESSIONS");
        
        SyntaxExamples.demonstrateSyntax();
        VariableCapture.demonstrateCapture();
        VariableScope.demonstrateScope();
        ClosureBehavior.demonstrateClosures();
        
    }
}
