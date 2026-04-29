package Week1.day4;

import java.util.*;
import java.util.function.*;
import java.util.stream.Stream;

public class FunctionalInterfaces {

    
    static class PredicateExamples {
        
        public static void demonstrate() {
            System.out.println("PREDICATE<T> ");
            System.out.println("Purpose: Test condition, return boolean\n");
            
            System.out.println("1. Check if number is even:");
            Predicate<Integer> isEven = n -> n % 2 == 0;
            List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
            numbers.stream()
                .filter(isEven)
                .forEach(n -> System.out.print(n + " "));
            System.out.println();
            
            System.out.println("\n2. Compose predicates (AND):");
            Predicate<Integer> greaterThan3 = n -> n > 3;
            Predicate<Integer> lessThan6 = n -> n < 6;
            Predicate<Integer> combined = greaterThan3.and(lessThan6);
            
            numbers.stream()
                .filter(combined)
                .forEach(n -> System.out.print(n + " "));
            System.out.println();
            
            System.out.println("\n3. Compose predicates (OR):");
            Predicate<Integer> lessOrEqual2 = n -> n <= 2;
            Predicate<Integer> greaterOrEqual5 = n -> n >= 5;
            Predicate<Integer> orCombined = lessOrEqual2.or(greaterOrEqual5);
            
            numbers.stream()
                .filter(orCombined)
                .forEach(n -> System.out.print(n + " "));
            System.out.println();
            
            System.out.println("\n4. Negate predicate (NOT):");
            Predicate<Integer> isOdd = isEven.negate();
            
            numbers.stream()
                .filter(isOdd)
                .forEach(n -> System.out.print(n + " "));
            System.out.println();
            
            System.out.println("\n5. String predicate:");
            Predicate<String> hasLength5 = s -> s.length() == 5;
            List<String> words = Arrays.asList("Hi", "Hello", "World", "Java");
            words.stream()
                .filter(hasLength5)
                .forEach(w -> System.out.print(w + " "));
            System.out.println();
        }
    }

    
    static class FunctionExamples {
        
        public static void demonstrate() {
            System.out.println("\n FUNCTION<T,R> ");
            System.out.println("Purpose: Transform T to R\n");
            
            System.out.println("1. Square numbers:");
            Function<Integer, Integer> square = x -> x * x;
            List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
            numbers.stream()
                .map(square)
                .forEach(n -> System.out.print(n + " "));
            System.out.println();
            
            System.out.println("\n2. Convert to String:");
            Function<Integer, String> toString = n -> "Number: " + n;
            numbers.stream()
                .map(toString)
                .forEach(s -> System.out.print(s + " | "));
            System.out.println();
            
            System.out.println("\n3. Chain functions with andThen:");
            Function<Integer, Integer> addTen = x -> x + 10;
            Function<Integer, Integer> multiplyBy2 = x -> x * 2;
            Function<Integer, Integer> combined = addTen.andThen(multiplyBy2);
            System.out.println("(1 + 10) * 2 = " + combined.apply(1));
            
            System.out.println("\n4. Chain functions with compose:");
            Function<Integer, Integer> composed = multiplyBy2.compose(addTen);
            System.out.println("(1 + 10) * 2 = " + composed.apply(1));
            
            System.out.println("\n5. IntFunction (input int, output object):");
            IntFunction<String> intToString = n -> "Int: " + n;
            String result = intToString.apply(42);
            System.out.println(result);
        }
    }

    
    static class ConsumerExamples {
        
        public static void demonstrate() {
            System.out.println("\n CONSUMER<T> ");
            System.out.println("Purpose: Perform action on T, return void\n");
            
            System.out.println("1. Print with prefix:");
            Consumer<String> printWithPrefix = s -> System.out.println(">> " + s);
            List<String> items = Arrays.asList("Item1", "Item2", "Item3");
            items.forEach(printWithPrefix);
            
            System.out.println("\n2. Accumulate in list:");
            List<Integer> accumulated = new ArrayList<>();
            Consumer<Integer> addToList = accumulated::add;
            Arrays.asList(10, 20, 30).forEach(addToList);
            System.out.println("Accumulated: " + accumulated);
            
            System.out.println("\n3. Chain consumers:");
            Consumer<String> print = s -> System.out.print(s);
            Consumer<String> printThenNewline = print.andThen(s -> System.out.println());
            
            items.forEach(item -> {
                print.accept(item);
                printThenNewline.accept(" (with newline)");
            });
            
            System.out.println("4. BiConsumer (two inputs):");
            BiConsumer<String, Integer> printWithCount = (s, count) -> 
                System.out.println(s + " x " + count);
            printWithCount.accept("Apple", 5);
            printWithCount.accept("Banana", 3);
        }
    }

    
    static class SupplierExamples {
        
        public static void demonstrate() {
            System.out.println("\n SUPPLIER");
            System.out.println("Purpose: generate T, takes no input\n");
            
            System.out.println("1. Generate random number:");
            Supplier<Integer> randomInt = () -> (int) (Math.random() * 100);
            System.out.println("Random: " + randomInt.get());
            System.out.println("Random: " + randomInt.get());
            System.out.println("Random: " + randomInt.get());
            
            System.out.println("\n2. Lazy initialization:");
            Supplier<List<String>> listSupplier = () -> new ArrayList<>(
                Arrays.asList("A", "B", "C")
            );
            List<String> list1 = listSupplier.get();
            List<String> list2 = listSupplier.get();
            System.out.println("List1: " + list1);
            System.out.println("List2: " + list2);
            System.out.println("Are they same object? " + (list1 == list2));
            
            System.out.println("\n3. Supplier with internal state:");
            Counter counter = new Counter();
            Supplier<Integer> counterSupplier = counter::increment;
            System.out.println("Count: " + counterSupplier.get());
            System.out.println("Count: " + counterSupplier.get());
            System.out.println("Count: " + counterSupplier.get());
            
            System.out.println("\n4. Generate values in stream:");
            Supplier<Integer> counter2 = new Counter()::increment;
            Stream.generate(counter2)
                .limit(5)
                .forEach(n -> System.out.print(n + " "));
            System.out.println();
        }
    }

    
    static class Counter {
        private int count = 0;
        
        public int increment() {
            return ++count;
        }
    }

    

    
    public static void main(String[] args) {
       
        
        PredicateExamples.demonstrate();
        FunctionExamples.demonstrate();
        ConsumerExamples.demonstrate();
        SupplierExamples.demonstrate();
     
      
    }
}
