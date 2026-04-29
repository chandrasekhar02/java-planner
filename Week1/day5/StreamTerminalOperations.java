package Week1.day5;

import java.util.*;

public class StreamTerminalOperations {

    static class Product {
        private String name;
        private double price;
        private int quantity;

        public Product(String name, double price, int quantity) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        public String getName() { return name; }
        public double getPrice() { return price; }
        public int getQuantity() { return quantity; }

        @Override
        public String toString() {
            return String.format("%s ($%.2f x %d)", name, price, quantity);
        }
    }

    static class ReduceExamples {
        public static void demonstrate() {
            System.out.println("REDUCE: Combine to Single Value \n");

            List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
            List<Product> products = Arrays.asList(
                new Product("Laptop", 999.99, 2),
                new Product("Mouse", 29.99, 5),
                new Product("Keyboard", 79.99, 3),
                new Product("Monitor", 299.99, 1)
            );

            System.out.println("1. Sum numbers with reduce:");
            int sum = numbers.stream()
                .reduce(0, (a, b) -> a + b);
            System.out.println("Sum: " + sum);

            System.out.println("\n2. Multiply numbers:");
            int product = numbers.stream()
                .reduce(1, (a, b) -> a * b);
            System.out.println("Product: " + product);

            System.out.println("\n3. Find maximum:");
            int max = numbers.stream()
                .reduce(0, (a, b) -> a > b ? a : b);
            System.out.println("Max: " + max);

            System.out.println("\n4. Reduce without initial value (returns Optional):");
            Optional<Integer> optionalSum = numbers.stream()
                .reduce((a, b) -> a + b);
            optionalSum.ifPresent(s -> System.out.println("Sum: " + s));

            System.out.println("\n5. Calculate total inventory value:");
            double total = products.stream()
                .map(p -> p.getPrice() * p.getQuantity())
                .reduce(0.0, (a, b) -> a + b);
            System.out.printf("Total value: $%.2f%n", total);

            System.out.println("\n6. Concatenate product names:");
            String names = products.stream()
                .map(Product::getName)
                .reduce("", (a, b) -> a + ", " + b)
                .replaceFirst("^, ", "");
            System.out.println("Products: " + names);
        }
    }

    static class FindFirstExamples {
        public static void demonstrate() {
            System.out.println("\n FINDFIRST: Get First Matching Element \n");

            List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
            List<Product> products = Arrays.asList(
                new Product("Laptop", 999.99, 2),
                new Product("Mouse", 29.99, 5),
                new Product("Keyboard", 79.99, 0),
                new Product("Monitor", 299.99, 1)
            );

            System.out.println("1. Find first even number:");
            Optional<Integer> firstEven = numbers.stream()
                .filter(n -> n % 2 == 0)
                .findFirst();
            firstEven.ifPresent(n -> System.out.println("First even: " + n));

            System.out.println("\n2. Find first number > 5:");
            numbers.stream()
                .filter(n -> n > 5)
                .findFirst()
                .ifPresent(n -> System.out.println("Found: " + n));

            System.out.println("\n3. Find first out-of-stock product:");
            products.stream()
                .filter(p -> p.getQuantity() == 0)
                .findFirst()
                .ifPresentOrElse(
                    p -> System.out.println("Out of stock: " + p),
                    () -> System.out.println("All products in stock")
                );

            System.out.println("\n4. Find first product > $500:");
            products.stream()
                .filter(p -> p.getPrice() > 500)
                .findFirst()
                .ifPresent(p -> System.out.println("Found: " + p));

            System.out.println("\n5. Find first, or return default:");
            String result = Arrays.asList("a", "b", "c").stream()
                .filter(s -> s.equals("x"))
                .findFirst()
                .orElse("not found");
            System.out.println("Result: " + result);
        }
    }

    static class AnyMatchExamples {
        public static void demonstrate() {
            System.out.println("\nANYMATCH\n");

            List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
            List<Product> products = Arrays.asList(
                new Product("Laptop", 999.99, 2),
                new Product("Mouse", 29.99, 5),
                new Product("Keyboard", 79.99, 0),
                new Product("Monitor", 299.99, 1)
            );

            System.out.println("1. Any even numbers");
            boolean hasEven = numbers.stream()
                .anyMatch(n -> n % 2 == 0);
            System.out.println("Has even: " + hasEven);

            System.out.println("\n2. Any number > 7?");
            boolean hasLarge = numbers.stream()
                .anyMatch(n -> n > 7);
            System.out.println("Has > 7: " + hasLarge);

            System.out.println("\n3. Any out-of-stock products?");
            boolean hasOutOfStock = products.stream()
                .anyMatch(p -> p.getQuantity() == 0);
            System.out.println("Out of stock exists: " + hasOutOfStock);

            System.out.println("\n4. Any product > $500?");
            boolean hasExpensive = products.stream()
                .anyMatch(p -> p.getPrice() > 500);
            System.out.println("Expensive product exists: " + hasExpensive);

            System.out.println("\n5. Any low stock items?");
            boolean hasLowStock = products.stream()
                .anyMatch(p -> p.getQuantity() < 2);
            System.out.println("Low stock exists: " + hasLowStock);
        }
    }

    static class AllMatchExamples {
        public static void demonstrate() {
            System.out.println("\nALLMATCH\n");

            List<Integer> numbers = Arrays.asList(2, 4, 6, 8, 10);
            List<Integer> mixed = Arrays.asList(1, 2, 3, 4, 5);
            List<Product> products = Arrays.asList(
                new Product("Laptop", 999.99, 2),
                new Product("Mouse", 29.99, 5),
                new Product("Keyboard", 79.99, 3),
                new Product("Monitor", 299.99, 1)
            );

            System.out.println("1. All numbers in [2,4,6,8,10] are even?");
            boolean allEven = numbers.stream()
                .allMatch(n -> n % 2 == 0);
            System.out.println("All even: " + allEven);

            System.out.println("\n2. All numbers in [1,2,3,4,5] are even?");
            boolean allEvenMixed = mixed.stream()
                .allMatch(n -> n % 2 == 0);
            System.out.println("All even: " + allEvenMixed);

            System.out.println("\n3. All numbers > 0?");
            boolean allPositive = numbers.stream()
                .allMatch(n -> n > 0);
            System.out.println("All positive: " + allPositive);

            System.out.println("\n4. All products in stock?");
            boolean allInStock = products.stream()
                .allMatch(p -> p.getQuantity() > 0);
            System.out.println("All in stock: " + allInStock);

            System.out.println("\n5. All products <= $1000?");
            boolean allAffordable = products.stream()
                .allMatch(p -> p.getPrice() <= 1000);
            System.out.println("All affordable: " + allAffordable);
        }
    }

    static class CombinedExamples {
        public static void demonstrate() {
            System.out.println("\nCOMBINED\n");

            List<Product> products = Arrays.asList(
                new Product("Laptop", 999.99, 2),
                new Product("Mouse", 29.99, 5),
                new Product("Keyboard", 79.99, 0),
                new Product("Monitor", 299.99, 1)
            );

            System.out.println("1. Inventory Health Check:");
            boolean lowStock = products.stream()
                .anyMatch(p -> p.getQuantity() < 2);
            boolean allAvailable = products.stream()
                .allMatch(p -> p.getQuantity() > 0);
            System.out.println("Low stock alert: " + lowStock);
            System.out.println("All available: " + allAvailable);

            System.out.println("\n2. First expensive in-stock item:");
            products.stream()
                .filter(p -> p.getPrice() > 100)
                .filter(p -> p.getQuantity() > 0)
                .findFirst()
                .ifPresent(p -> System.out.println("Found: " + p));

            System.out.println("\n3. Order summary:");
            double subtotal = products.stream()
                .filter(p -> p.getQuantity() > 0)
                .map(p -> p.getPrice() * p.getQuantity())
                .reduce(0.0, (a, b) -> a + b);
            System.out.printf("Subtotal: $%.2f%n", subtotal);
        }
    }

    public static void main(String[] args) {
        System.out.println(" STREAM TERMINAL OPERATIONS\n");

        ReduceExamples.demonstrate();
        FindFirstExamples.demonstrate();
        AnyMatchExamples.demonstrate();
        AllMatchExamples.demonstrate();
        CombinedExamples.demonstrate();

    }
}
