package Week1.day4;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class PhonebookWithLambdas {

    static class Contact {
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private String email;
        private int age;

        public Contact(String firstName, String lastName, String phoneNumber, String email, int age) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.phoneNumber = phoneNumber;
            this.email = email;
            this.age = age;
        }

        public String getFirstName() { return firstName; }
        public String getLastName() { return lastName; }
        public String getPhoneNumber() { return phoneNumber; }
        public String getEmail() { return email; }
        public int getAge() { return age; }

        @Override
        public String toString() {
            return String.format("%-15s %-15s | %-12s | %-25s | Age: %d",
                firstName, lastName, phoneNumber, email, age);
        }
    }

    static class Phonebook {
        private List<Contact> contacts = new ArrayList<>();

        public void add(Contact contact) {
            contacts.add(contact);
        }

      
        public Optional<Contact> findByPhone(String phone) {
            return contacts.stream()
                .filter(c -> c.getPhoneNumber().equals(phone))
                .findFirst();
        }

      
        public Optional<Contact> findByEmail(String email) {
            return contacts.stream()
                .filter(c -> c.getEmail().equals(email))
                .findFirst();
        }

        public Optional<Contact> findByFullName(String first, String last) {
            return contacts.stream()
                .filter(c -> c.getFirstName().equalsIgnoreCase(first) &&
                           c.getLastName().equalsIgnoreCase(last))
                .findFirst();
        }

        public List<Contact> findAll(Predicate<Contact> predicate) {
            return contacts.stream()
                .filter(predicate)
                .toList();
        }

        
        public List<Contact> filterByLastName(String lastName) {
            return findAll(c -> c.getLastName().equalsIgnoreCase(lastName));
        }

     
        public List<Contact> filterByAgeRange(int minAge, int maxAge) {
            return findAll(c -> c.getAge() >= minAge && c.getAge() <= maxAge);
        }

        
        public List<Contact> filterByEmailDomain(String domain) {
            return findAll(c -> c.getEmail().endsWith("@" + domain));
        }

        
        public List<Contact> filter(Predicate<Contact> condition) {
            return findAll(condition);
        }

        
        public List<Contact> sortByFullName() {
            return contacts.stream()
                .sorted(Comparator.comparing(Contact::getLastName)
                                  .thenComparing(Contact::getFirstName))
                .toList();
        }

      
        public List<Contact> sortByAgeThenName() {
            return contacts.stream()
                .sorted(Comparator.comparingInt(Contact::getAge)
                                  .thenComparing(Contact::getLastName))
                .toList();
        }

       
        public List<Contact> sortByAgeDescending() {
            return contacts.stream()
                .sorted(Comparator.comparingInt(Contact::getAge).reversed())
                .toList();
        }

        
        public List<Contact> sortByEmail() {
            return contacts.stream()
                .sorted(Comparator.comparing(Contact::getEmail))
                .toList();
        }

        public List<Contact> sort(Comparator<Contact> comparator) {
            return contacts.stream()
                .sorted(comparator)
                .toList();
        }

       
        public List<String> getEmails() {
            return contacts.stream()
                .map(Contact::getEmail)
                .toList();
        }

        public List<String> getFullNames() {
            return contacts.stream()
                .map(c -> c.getFirstName() + " " + c.getLastName())
                .toList();
        }

      
        public List<String> getPhones() {
            return contacts.stream()
                .map(Contact::getPhoneNumber)
                .toList();
        }

        public <R> List<R> transform(Function<Contact, R> transformer) {
            return contacts.stream()
                .map(transformer)
                .toList();
        }

    
        public Optional<Contact> getOldest() {
            return contacts.stream()
                .max(Comparator.comparingInt(Contact::getAge));
        }

        public Optional<Contact> getYoungest() {
            return contacts.stream()
                .min(Comparator.comparingInt(Contact::getAge));
        }

       
        public long count(Predicate<Contact> predicate) {
            return contacts.stream()
                .filter(predicate)
                .count();
        }

        
        public double getAverageAge() {
            return contacts.stream()
                .mapToInt(Contact::getAge)
                .average()
                .orElse(0);
        }

        public void displayMatching(Predicate<Contact> condition, Consumer<Contact> display) {
            contacts.stream()
                .filter(condition)
                .forEach(display);
        }

       
        public List<String> getEmailsOfAdults() {
            return contacts.stream()
                .filter(c -> c.getAge() >= 18)
                .map(Contact::getEmail)
                .toList();
        }

       
        public Map<String, List<Contact>> groupByLastName() {
            return contacts.stream()
                .collect(java.util.stream.Collectors
                    .groupingBy(Contact::getLastName));
        }

      
        public void printTable(List<Contact> list) {
            if (list.isEmpty()) {
                System.out.println("No contacts.");
                return;
            }
            System.out.println("\n" + "=".repeat(105));
            System.out.printf("%-15s %-15s | %-12s | %-25s | %s%n",
                "First Name", "Last Name", "Phone", "Email", "Age");
            System.out.println("=".repeat(105));
            list.forEach(System.out::println);
            System.out.println("=".repeat(105));
        }

        public int size() {
            return contacts.size();
        }
    }

    

    public static void main(String[] args) {
        System.out.println("===== PHONEBOOK WITH LAMBDAS AND METHOD REFERENCES =====\n");

        Phonebook phonebook = new Phonebook();

        phonebook.add(new Contact("John", "Smith", "555-0001", "john@gmail.com", 28));
        phonebook.add(new Contact("Alice", "Johnson", "555-0002", "alice@gmail.com", 35));
        phonebook.add(new Contact("Bob", "Smith", "555-0003", "bob@yahoo.com", 42));
        phonebook.add(new Contact("Charlie", "Brown", "555-0004", "charlie@example.com", 25));
        phonebook.add(new Contact("Diana", "Johnson", "555-0005", "diana@example.com", 31));
        phonebook.add(new Contact("Eve", "Davis", "555-0006", "eve@gmail.com", 29));

        System.out.println("1. SEARCH WITH OPTIONAL & LAMBDA:");
        phonebook.findByPhone("555-0001")
            .ifPresentOrElse(
                c -> System.out.println("Found: " + c),
                () -> System.out.println("Not found")
            );

        phonebook.findByEmail("nobody@example.com")
            .ifPresentOrElse(
                c -> System.out.println(" Found: " + c),
                () -> System.out.println(" Not found: nobody@example.com")
            );

        phonebook.findByFullName("Alice", "Johnson")
            .ifPresent(c -> System.out.println(" Found: " + c));
        System.out.println();

        System.out.println("2. FILTER WITH LAMBDA PREDICATES:");

        System.out.println("\nAll 'Smith' contacts:");
        phonebook.printTable(phonebook.filterByLastName("Smith"));

        System.out.println("Contacts aged 25-30:");
        phonebook.printTable(phonebook.filterByAgeRange(25, 30));

        System.out.println("Contacts with Gmail:");
        phonebook.printTable(phonebook.filterByEmailDomain("gmail.com"));

        System.out.println("Custom filter (age >= 30):");
        phonebook.printTable(phonebook.filter(c -> c.getAge() >= 30));
        System.out.println();

       
        System.out.println("3. SORT WITH METHOD REFERENCES:");

        System.out.println("\nSorted by last name, then first name:");
        phonebook.printTable(phonebook.sortByFullName());

        System.out.println("Sorted by age (oldest first):");
        phonebook.printTable(phonebook.sortByAgeDescending());

        System.out.println("Sorted by age, then name:");
        phonebook.printTable(phonebook.sortByAgeThenName());

        System.out.println("Custom sort (by email):");
        phonebook.printTable(phonebook.sortByEmail());
        System.out.println();

      
        System.out.println("4. TRANSFORM WITH FUNCTION:");

        System.out.println("\nAll emails:");
        phonebook.getEmails().forEach(e -> System.out.println("  " + e));

        System.out.println("\nAll full names:");
        phonebook.getFullNames().forEach(n -> System.out.println("  " + n));

        System.out.println("\nCustom transform (email + age):");
        phonebook.transform(c -> c.getEmail() + " (age " + c.getAge() + ")")
            .forEach(s -> System.out.println("  " + s));
        System.out.println();

       
        System.out.println("5. STATISTICS:");

        phonebook.getOldest()
            .ifPresent(c -> System.out.println("Oldest: " + c.getFirstName() + 
                                             " (age " + c.getAge() + ")"));

        phonebook.getYoungest()
            .ifPresent(c -> System.out.println("Youngest: " + c.getFirstName() + 
                                             " (age " + c.getAge() + ")"));

        System.out.println("Adults (>= 18): " + phonebook.count(c -> c.getAge() >= 18));
        System.out.println("Seniors (>= 30): " + phonebook.count(c -> c.getAge() >= 30));
        System.out.printf("Average age: %.1f%n", phonebook.getAverageAge());
        System.out.println();

      
        System.out.println("6. COMBINED OPERATIONS:");

        System.out.println("\nAdult emails:");
        phonebook.getEmailsOfAdults().forEach(e -> System.out.println("  " + e));

        System.out.println("\nGrouped by last name:");
        phonebook.groupByLastName().forEach((name, list) -> {
            System.out.println("  " + name + ": " + list.size() + " contact(s)");
            list.forEach(c -> System.out.println("    - " + c.getFirstName()));
        });

        System.out.println("\nDisplay adults with custom formatting:");
        phonebook.displayMatching(
            c -> c.getAge() >= 30,
            c -> System.out.println("   " + c.getFirstName() + " " + c.getLastName() + 
                                   ", age " + c.getAge())
        );

        System.out.println("\n" + "=".repeat(50));
        System.out.println("Total contacts: " + phonebook.size());
        System.out.println("=".repeat(50));
    }
}
