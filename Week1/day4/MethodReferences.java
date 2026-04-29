package Week1.day4;

import java.util.*;

public class MethodReferences {

    
    static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return String.format("%s (%d)", name, age);
        }
    }

    static class PersonUtils {
        public static int compareByAge(Person p1, Person p2) {
            return Integer.compare(p1.getAge(), p2.getAge());
        }

        public static void printPerson(Person p) {
            System.out.println("  " + p);
        }

        public static boolean isAdult(Person p) {
            return p.getAge() >= 18;
        }
    }

    
    static class Form1StaticMethod {
        
        public static void demonstrate() {
            System.out.println(" STATIC METHOD REFERENCE ");
            System.out.println("Syntax: ClassName::staticMethodName\n");
            
            List<Person> people = Arrays.asList(
                new Person("Alice", 25),
                new Person("Bob", 30),
                new Person("Charlie", 20)
            );

            System.out.println("1. Static method for sorting:");
            people.sort(PersonUtils::compareByAge);
            people.forEach(p -> System.out.println("  " + p));

            System.out.println("\n2. Static method for filtering:");
            List<Person> adults = people.stream()
                .filter(PersonUtils::isAdult)
                .toList();
            System.out.println("Adults: " + adults);

            System.out.println("\n3. Static method for consuming:");
            people.forEach(PersonUtils::printPerson);
        }
    }

    
    static class Form2InstanceMethod {
        
        public static void demonstrate() {
            System.out.println("\n FORM 2: INSTANCE METHOD REFERENCE");
            System.out.println("Syntax: objectReference::instanceMethodName\n");

            List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

            System.out.println("1. Print using System.out.println:");
            names.forEach(System.out::println);

            System.out.println("\n2. Add using list method:");
            List<String> collected = new ArrayList<>();
            names.forEach(collected::add);
            System.out.println("Collected: " + collected);

            System.out.println("\n3. Find index using list method:");
            List<String> fruits = Arrays.asList("Apple", "Banana", "Cherry");
            Integer index = fruits.stream()
                .filter(f -> f.startsWith("B"))
                .map(fruits::indexOf)
                .findFirst()
                .orElse(-1);
            System.out.println("Index of fruit starting with B: " + index);

            System.out.println("\n4. String methods:");
            List<String> words = Arrays.asList("hello", "world");
            
            List<Integer> lengths = words.stream()
                .map(String::length)
                .toList();
            System.out.println("Lengths: " + lengths);

            List<String> upper = words.stream()
                .map(String::toUpperCase)
                .toList();
            System.out.println("Uppercase: " + upper);
        }
    }

    
    static class Form3ConstructorReference {
        
        public static void demonstrate() {
            System.out.println("\n FORM 3: CONSTRUCTOR REFERENCE ");
            System.out.println("Syntax: ClassName::new\n");

            System.out.println("1. String constructor:");
            java.util.function.Function<String, String> stringCreator = String::new;
            String created = stringCreator.apply("Hello");
            System.out.println("Created: " + created);

            System.out.println("\n2. Integer constructor (parse):");
            java.util.function.Function<String, Integer> intParser = Integer::new;
            Integer num = intParser.apply("42");
            System.out.println("Parsed: " + num);

            System.out.println("\n3. Person constructor (BiFunction):");
            java.util.function.BiFunction<String, Integer, Person> personCreator = Person::new;
            Person p1 = personCreator.apply("John", 28);
            System.out.println("Created: " + p1);

            System.out.println("\n4. Create objects from data:");
            List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
            
            java.util.function.Supplier<HashSet<String>> setCreator = HashSet::new;
            HashSet<String> nameSet = setCreator.get();
            names.forEach(nameSet::add);
            System.out.println("Created set: " + nameSet);

            System.out.println("\n5. Array constructor:");
            java.util.function.IntFunction<String[]> arrayCreator = String[]::new;
            String[] array = arrayCreator.apply(5);
            System.out.println("Created array of length: " + array.length);
        }
    }

    
    static class Form4BoundedType {
        
        public static void demonstrate() {
            System.out.println("\n INSTANCE METHOD ");
            System.out.println("Syntax: ClassName::instanceMethodName (for inherited/interface methods)\n");

            System.out.println("1. Object methods:");
            List<Person> people = Arrays.asList(
                new Person("Alice", 25),
                new Person("Bob", 30)
            );

            Person alice = people.get(0);
            List<Person> matched = people.stream()
                .filter(alice::equals)
                .toList();
            System.out.println("Matching persons: " + matched);

            List<String> strings = people.stream()
                .map(Person::toString)
                .toList();
            System.out.println("As strings: " + strings);

            System.out.println("\n2. Generic instance method (implicit this parameter):");
            List<String> words = Arrays.asList("hello", "world", "lambda");
            
            List<Boolean> containsL = words.stream()
                .map(word -> word.contains("l"))
                .toList();
            System.out.println("Contains 'l': " + containsL);
        }
    }


    
    public static void main(String[] args) {

        Form1StaticMethod.demonstrate();
        Form2InstanceMethod.demonstrate();
        Form3ConstructorReference.demonstrate();
        Form4BoundedType.demonstrate();
      

    }
}
