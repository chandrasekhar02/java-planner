package Week1.day4;

import java.util.*;
import java.util.function.*;

public class ComposingFunctions {

    
    static class Examples {
        
        public static void demonstrate() {
      
            System.out.println("f1.andThen(f2) = f2(f1(x))\n");

            System.out.println("1. Chain transformations:");
            Function<Integer, Integer> addTen = x -> {
                System.out.println("  Step 1: Add 10 to " + x);
                return x + 10;
            };
            
            Function<Integer, Integer> multiplyBy2 = x -> {
                System.out.println("  Step 2: Multiply " + x + " by 2");
                return x * 2;
            };
            
            Function<Integer, Integer> pipeline = addTen.andThen(multiplyBy2);
            System.out.println("Starting with 5:");
            int result = pipeline.apply(5);
            System.out.println("Final result: " + result + "\n");

            System.out.println("2. String transformations:");
            Function<String, String> trim = String::trim;
            Function<String, String> uppercase = String::toUpperCase;
            Function<String, Integer> length = String::length;
            
            Function<String, Integer> process = trim
                .andThen(uppercase)
                .andThen(length);
            
            String input = "  hello  ";
            System.out.println("Input: '" + input + "'");
            System.out.println("After trim + uppercase + length: " + process.apply(input) + "\n");

            System.out.println("3. Multiple chained operations:");
            Function<Double, Double> halve = x -> x / 2;
            Function<Double, Double> square = x -> x * x;
            Function<Double, Double> addOne = x -> x + 1;
            
            Function<Double, Double> complex = halve
                .andThen(square)
                .andThen(addOne);
            
            System.out.println("10.0 -> halve -> square -> add1 = " + complex.apply(10.0));
            System.out.println("(((10 / 2) ^ 2) + 1) = (5^2 + 1) = 26.0\n");

            System.out.println("4. Mixed lambdas and method references:");
            Function<String, Integer> convertToInt = Integer::parseInt;
            Function<Integer, Integer> double_ = x -> x * 2;
            Function<Integer, String> toHex = Integer::toHexString;
            
            Function<String, String> stringToHex = convertToInt
                .andThen(double_)
                .andThen(toHex);
            
            System.out.println("Convert '10' -> to int -> double -> to hex:");
            System.out.println("'10' -> " + stringToHex.apply("10"));
        }
    }

    
    static class ComposeExamples {
        
        public static void demonstrate() {
            System.out.println("\n COMPOSE()");
            System.out.println("f1.compose(f2) = f1(f2(x))\n");

            System.out.println("1. Compose reverses order:");
            Function<Integer, Integer> addTen = x -> {
                System.out.println("  Step A: Add 10 to " + x);
                return x + 10;
            };
            
            Function<Integer, Integer> multiplyBy2 = x -> {
                System.out.println("  Step B: Multiply " + x + " by 2");
                return x * 2;
            };
            
         
            Function<Integer, Integer> pipelineCompose = addTen.compose(multiplyBy2);
            System.out.println("Starting with 5:");
            int result = pipelineCompose.apply(5);
            System.out.println("Final result: " + result + "\n");

            System.out.println("2. Data validation chain:");
            Function<String, String> validate = s -> {
                if (s == null || s.isEmpty()) throw new IllegalArgumentException("Empty string");
                return s;
            };
            
            Function<String, String> normalize = s -> s.trim().toLowerCase();
            
            Function<String, String> safeNormalize = validate.compose(normalize);
            
            String input = "  HELLO  ";
            System.out.println("Input: '" + input + "'");
            System.out.println("After normalize then validate: '" + safeNormalize.apply(input) + "'\n");

            System.out.println("3. Complex computation:");
            Function<Double, Double> sqrt = Math::sqrt;
            Function<Double, Double> absolute = Math::abs;
            Function<Double, Double> addOne = x -> x + 1;
            
            Function<Double, Double> computation = sqrt
                .compose(absolute)
                .compose(addOne);
            
            System.out.println("sqrt(abs(x + 1)) where x = -5:");
            System.out.println("sqrt(abs(-5 + 1)) = sqrt(abs(-4)) = sqrt(4) = " + 
                computation.apply(-5.0));
        }
    }

    
    static class ComparisonExample {
        
        public static void demonstrate() {
            System.out.println("\n");

            Function<Integer, Integer> f = x -> x * 2;
            Function<Integer, Integer> g = x -> x + 10;

            System.out.println("Given: f(x) = x * 2, g(x) = x + 10");
            System.out.println("Input: 5\n");

            int andThenResult = f.andThen(g).apply(5);
            System.out.println("f.andThen(g) = g(f(5))");
            System.out.println("  = g(5 * 2)");
            System.out.println("  = g(10)");
            System.out.println("  = 10 + 10");
            System.out.println("  = " + andThenResult);

            System.out.println();

            int composeResult = g.compose(f).apply(5);
            System.out.println("g.compose(f) = g(f(5))");
            System.out.println("  = g(5 * 2)");
            System.out.println("  = g(10)");
            System.out.println("  = 10 + 10");
            System.out.println("  = " + composeResult);

        }
    }

    
    static class Consumers {
        
        public static void demonstrate() {
            System.out.println("\n CONSUMER \n");

            Consumer<String> printLine = s -> System.out.println("  " + s);
            Consumer<String> printDashes = s -> System.out.println("  ---");
            Consumer<String> printUpper = s -> System.out.println("  " + s.toUpperCase());

            Consumer<String> combined = printLine
                .andThen(printUpper)
                .andThen(printDashes);

            combined.accept("Hello");
            System.out.println();
            combined.accept("World");
        }
    }

    
    static class PredicateCompositionExample {
        
        public static void demonstrate() {
            System.out.println("\n PREDICATE COMPOSITION\n");

            Predicate<Integer> isEven = n -> n % 2 == 0;
            Predicate<Integer> isGreaterThan5 = n -> n > 5;
            Predicate<Integer> isLessThan20 = n -> n < 20;

            Predicate<Integer> validNumber = isEven
                .and(isGreaterThan5)
                .and(isLessThan20);

            List<Integer> numbers = Arrays.asList(2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22);
            
            System.out.println("Filter even numbers between 5 and 20:");
            numbers.stream()
                .filter(validNumber)
                .forEach(n -> System.out.print(n + " "));
            System.out.println();
        }
    }

    
    static class Example {
        
        static class User {
            private String email;
            private int age;
            private boolean active;

            public User(String email, int age, boolean active) {
                this.email = email;
                this.age = age;
                this.active = active;
            }

            public String getEmail() {
                return email;
            }

            public int getAge() {
                return age;
            }

            public boolean isActive() {
                return active;
            }

            @Override
            public String toString() {
                return String.format("%s (age %d, active: %s)", email, age, active);
            }
        }

        public static void demonstrate() {
          
            List<User> users = Arrays.asList(
                new User("alice@example.com", 25, true),
                new User("bob@example.com", 17, true),
                new User("charlie@example.com", 30, false),
                new User("diana@example.com", 28, true)
            );

            Predicate<User> isActive = User::isActive;
            Predicate<User> isAdult = u -> u.getAge() >= 18;
            
            Function<User, String> domain = u -> u.getEmail().split("@")[1];

            System.out.println("All users:");
            users.forEach(u -> System.out.println("  " + u));

            System.out.println("\nActive adult users:");
            users.stream()
                .filter(isActive.and(isAdult))
                .forEach(u -> System.out.println("  " + u));

            System.out.println("\nEmail domains:");
            users.stream()
                .map(domain)
                .distinct()
                .forEach(d -> System.out.println("  " + d));
        }
    }

    
    public static void main(String[] args) {
        System.out.println("COMPOSING FUNCTIONS\n");

        Examples.demonstrate();
        ComposeExamples.demonstrate();
        ComparisonExample.demonstrate();
        Consumers.demonstrate();
        PredicateCompositionExample.demonstrate();
        Examples.demonstrate();

    }
}
