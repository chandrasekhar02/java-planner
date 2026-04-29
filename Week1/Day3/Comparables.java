package Week1.Day3;

import java.util.*;


public class Comparables{


    static class Person implements Comparable<Person> {
        private String name;
        private int age;
        private double salary;

        public Person(String name, int age, double salary) {
            this.name = name;
            this.age = age;
            this.salary = salary;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public double getSalary() {
            return salary;
        }

        @Override
        public int compareTo(Person other) {
         
            return Integer.compare(this.age, other.age);
        }

        @Override
        public String toString() {
            return String.format("%s (Age: %d, Salary: $%.2f)", name, age, salary);
        }
    }


    static class NameComparator implements Comparator<Person> {
        @Override
        public int compare(Person p1, Person p2) {
            return p1.getName().compareTo(p2.getName());
        }
    }

   
    static class SalaryComparatorDesc implements Comparator<Person> {
        @Override
        public int compare(Person p1, Person p2) {
            // Reverse order: negative of comparison
            return Double.compare(p2.getSalary(), p1.getSalary());
        }
    }

    static class SalaryComparatorAsc implements Comparator<Person> {
        @Override
        public int compare(Person p1, Person p2) {
            return Double.compare(p1.getSalary(), p2.getSalary());
        }
    }

   
    static class ComparatorExamples {

        static Comparator<Person> byAge = (p1, p2) -> Integer.compare(p1.getAge(), p2.getAge());

        static Comparator<Person> byName = (p1, p2) -> p1.getName().compareTo(p2.getName());

  
        static Comparator<Person> bySalaryDesc = (p1, p2) -> 
            Double.compare(p2.getSalary(), p1.getSalary());

      
      
    }

    
    static class ChainedComparators {
        static Comparator<Person> salaryThenName =
            Comparator.comparingDouble(Person::getSalary).reversed()
                      .thenComparing(Person::getName);

        static Comparator<Person> ageThenSalaryDesc =
            Comparator.comparingInt(Person::getAge)
                      .thenComparingDouble(Person::getSalary).reversed();

    
        static Comparator<Person> nameByAge =
            Comparator.comparing(Person::getName)
                      .thenComparingInt(Person::getAge);
    }

    
    public static void main(String[] args) {
        System.out.println("COMPARABLE \n");

   
        List<Person> people = Arrays.asList(
            new Person("Alice", 30, 75000),
            new Person("Bob", 25, 65000),
            new Person("Charlie", 35, 85000),
            new Person("Diana", 28, 70000),
            new Person("Eve", 32, 80000)
        );

        System.out.println("Original List:");
        people.forEach(System.out::println);
        System.out.println();

        
        System.out.println("1. COMPARABLE");
        List<Person> peopleByAge = new ArrayList<>(people);
        Collections.sort(peopleByAge); 
        peopleByAge.forEach(System.out::println);
        System.out.println();

    
        System.out.println("2. COMPARATOR: Sort by name");
        List<Person> peopleByName = new ArrayList<>(people);
        Collections.sort(peopleByName, new NameComparator());
        peopleByName.forEach(System.out::println);
        System.out.println();

       
        System.out.println("3. COMPARATOR: Sort by salary (descending)");
        List<Person> peopleBySalaryDesc = new ArrayList<>(people);
        Collections.sort(peopleBySalaryDesc, new SalaryComparatorDesc());
        peopleBySalaryDesc.forEach(System.out::println);
        System.out.println();

        
        System.out.println("4. LAMBDA: Sort by salary (ascending)");
        List<Person> peopleBySalaryAsc = new ArrayList<>(people);
        Collections.sort(peopleBySalaryAsc, (p1, p2) -> 
            Double.compare(p1.getSalary(), p2.getSalary()));
        peopleBySalaryAsc.forEach(System.out::println);
        System.out.println();

    
        System.out.println("5.Sort by age method reference");
        List<Person> peopleByAgeMethodRef = new ArrayList<>(people);
        peopleByAgeMethodRef.sort(Comparator.comparingInt(Person::getAge));
        peopleByAgeMethodRef.forEach(System.out::println);
        System.out.println();

      
        System.out.println("6.Sort by salary DESC, then by name ASC");
        List<Person> peopleChained = new ArrayList<>(people);
        peopleChained.sort(
            Comparator.comparingDouble(Person::getSalary).reversed()
                      .thenComparing(Person::getName)
        );
        peopleChained.forEach(System.out::println);
        System.out.println();

        System.out.println("7. Sort by age, then by salary DESC");
        List<Person> peopleMultiChain = new ArrayList<>(people);
        peopleMultiChain.sort(
            Comparator.comparingInt(Person::getAge)
                      .thenComparingDouble(Person::getSalary).reversed()
        );
        peopleMultiChain.forEach(System.out::println);
        System.out.println();

        System.out.println("8. REVERSE: Sort by name in reverse");
        List<Person> peopleReverse = new ArrayList<>(people);
        peopleReverse.sort(Comparator.comparing(Person::getName).reversed());
        peopleReverse.forEach(System.out::println);
        System.out.println();

      
    }
}
