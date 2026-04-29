package Week1.Day3;

import java.util.*;

/**
 * Enhanced Phonebook with Advanced Collection Features
 * 
 * Features:
 * - Sort by multiple fields using Comparator.comparing().thenComparing()
 * - Optional return types for safe null handling
 * - Search by various criteria
 * - Advanced filtering and sorting
 */
public class EnhancedPhonebook {

    static class Contact implements Comparable<Contact> {
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

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getEmail() {
            return email;
        }

        public int getAge() {
            return age;
        }

        @Override
        public int compareTo(Contact other) {
            int lastNameCompare = this.lastName.compareTo(other.lastName);
            if (lastNameCompare != 0) {
                return lastNameCompare;
            }
            return this.firstName.compareTo(other.firstName);
        }

        @Override
        public String toString() {
            return String.format("%-15s %-15s || %-12s || %-25s || Age: %d",
                firstName, lastName, phoneNumber, email, age);
        }
    }

    static class Phonebook {
        private Map<String, Contact> phoneMap = new HashMap<>();  // phone -> contact
        private Map<String, Contact> emailMap = new HashMap<>();   // email -> contact
        private List<Contact> contacts = new ArrayList<>();         // All contacts

      
        public void addContact(Contact contact) {
            phoneMap.put(contact.getPhoneNumber(), contact);
            emailMap.put(contact.getEmail(), contact);
            contacts.add(contact);
        }

       
        public boolean removeContact(String phoneNumber) {
            Optional<Contact> contact = findByPhone(phoneNumber);
            if (contact.isPresent()) {
                Contact c = contact.get();
                phoneMap.remove(phoneNumber);
                emailMap.remove(c.getEmail());
                contacts.remove(c);
                return true;
            }
            return false;
        }

       

        public Optional<Contact> findByPhone(String phoneNumber) {
            return Optional.ofNullable(phoneMap.get(phoneNumber));
        }

        public Optional<Contact> findByEmail(String email) {
            return Optional.ofNullable(emailMap.get(email));
        }

        public Optional<Contact> findByFullName(String firstName, String lastName) {
            return contacts.stream()
                .filter(c -> c.getFirstName().equalsIgnoreCase(firstName) &&
                           c.getLastName().equalsIgnoreCase(lastName))
                .findFirst();
        }

        public List<Contact> findByFirstName(String firstName) {
            return contacts.stream()
                .filter(c -> c.getFirstName().equalsIgnoreCase(firstName))
                .toList();
        }

        
        public List<Contact> findByLastName(String lastName) {
            return contacts.stream()
                .filter(c -> c.getLastName().equalsIgnoreCase(lastName))
                .toList();
        }

     
        public List<Contact> sortByFullName() {
            return contacts.stream()
                .sorted()  
                .toList();
        }

       
        public List<Contact> sortByLastNameThenFirstName() {
            return contacts.stream()
                .sorted(Comparator.comparing(Contact::getLastName).reversed()
                                  .thenComparing(Contact::getFirstName))
                .toList();
        }

  
        public List<Contact> sortByAgeThenName() {
            return contacts.stream()
                .sorted(Comparator.comparingInt(Contact::getAge)
                                  .thenComparing(Contact::getLastName))
                .toList();
        }

  
        public List<Contact> sortByLastNameThenAgeDescThenFirst() {
            return contacts.stream()
                .sorted(Comparator.comparing(Contact::getLastName)
                                  .thenComparingInt(Contact::getAge).reversed()
                                  .thenComparing(Contact::getFirstName))
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

      
        public List<Contact> getByAgeRange(int minAge, int maxAge) {
            return contacts.stream()
                .filter(c -> c.getAge() >= minAge && c.getAge() <= maxAge)
                .sorted(Comparator.comparing(Contact::getLastName))
                .toList();
        }

      
        public List<Contact> getByEmailDomain(String domain) {
            return contacts.stream()
                .filter(c -> c.getEmail().endsWith("@" + domain))
                .sorted(Comparator.comparing(Contact::getLastName))
                .toList();
        }

    
        public Optional<Contact> getOldestContact() {
            return contacts.stream()
                .max(Comparator.comparingInt(Contact::getAge));
        }

       
        public Optional<Contact> getYoungestContact() {
            return contacts.stream()
                .min(Comparator.comparingInt(Contact::getAge));
        }

      
        public List<Contact> getAllContacts() {
            return new ArrayList<>(contacts);
        }

        public int getContactCount() {
            return contacts.size();
        }

       
        public void printContacts(List<Contact> contactList) {
            if (contactList.isEmpty()) {
                System.out.println("No contacts to display.");
                return;
            }
            System.out.println("\n" + "=".repeat(100));
            System.out.printf("%-15s %-15s || %-12s || %-25s || %s%n",
                "First Name", "Last Name", "Phone", "Email", "Age");
            System.out.println("=".repeat(100));
            contactList.forEach(System.out::println);
            System.out.println("=".repeat(100) + "\n");
        }
    }

  
    public static void main(String[] args) {
        System.out.println("PHONEBOOK\n");

        Phonebook phonebook = new Phonebook();

        phonebook.addContact(new Contact("John", "Smith", "555-0001", "john.smith@gmail.com", 28));
        phonebook.addContact(new Contact("Alice", "Johnson", "555-0002", "alice.j@gmail.com", 35));
        phonebook.addContact(new Contact("Bob", "Smith", "555-0003", "bob.smith@yahoo.com", 42));
        phonebook.addContact(new Contact("Charlie", "Brown", "555-0004", "charlie@gmail.com", 25));
        phonebook.addContact(new Contact("Diana", "Johnson", "555-0005", "diana.j@yahoo.com", 31));
        phonebook.addContact(new Contact("Eve", "Davis", "555-0006", "eve.davis@gmail.com", 29));
        phonebook.addContact(new Contact("Frank", "Wilson", "555-0007", "frank.w@outlook.com", 38));
        phonebook.addContact(new Contact("Grace", "Taylor", "555-0008", "grace.t@gmail.com", 26));

        
        System.out.println("1. OPTIONAL SEARCH EXAMPLES:");
        
       
        String searchPhone = "555-0001";
        phonebook.findByPhone(searchPhone)
            .ifPresentOrElse(
                contact -> System.out.println("Found: " + contact),
                () -> System.out.println("✗ Contact not found for: " + searchPhone)
            );

  
        String searchEmail = "nobody@example.com";
        String result = phonebook.findByEmail(searchEmail)
            .map(c -> "Email: " + c.getEmail())
            .orElse("Email not found: " + searchEmail);
        System.out.println(result);

        
        System.out.println("\nOldest contact:");
        phonebook.getOldestContact()
            .ifPresent(c -> System.out.println("  " + c));

        System.out.println("\nYoungest contact:");
        phonebook.getYoungestContact()
            .ifPresent(c -> System.out.println("  " + c));
        System.out.println();

       
        System.out.println("2. SORT BY MULTIPLE FIELDS:");

        System.out.println("\nSort by Last Name, then First Name (Natural) ");
        phonebook.printContacts(phonebook.sortByFullName());

        System.out.println(" Sort by Last Name DESC, then First Name ASC ");
        phonebook.printContacts(phonebook.sortByLastNameThenFirstName());

        System.out.println(" Sort by Age ASC, then Last Name ");
        phonebook.printContacts(phonebook.sortByAgeThenName());

        System.out.println(" Sort by Last Name, Age DESC, First Name ");
        phonebook.printContacts(phonebook.sortByLastNameThenAgeDescThenFirst());

        System.out.println("Sort by Age DESC ");
        phonebook.printContacts(phonebook.sortByAgeDescending());

        System.out.println(" Sort by Email ");
        phonebook.printContacts(phonebook.sortByEmail());

        System.out.println("3. FILTERING AND SEARCHING:");

        List<Contact> smiths = phonebook.findByLastName("Smith");
        System.out.println("All contacts with last name 'Smith':");
        phonebook.printContacts(smiths);

        List<Contact> johnsons = phonebook.findByLastName("Johnson");
        System.out.println("All contacts with last name 'Johnson':");
        phonebook.printContacts(johnsons);

        System.out.println("Contacts aged 25-30:");
        phonebook.printContacts(phonebook.getByAgeRange(25, 30));

        System.out.println("Contacts with Gmail:");
        phonebook.printContacts(phonebook.getByEmailDomain("gmail.com"));

        System.out.println("4. FULL NAME SEARCH:");
        phonebook.findByFullName("John", "Smith")
            .ifPresentOrElse(
                contact -> {
                    System.out.println("Found contact:");
                    System.out.println("  Phone: " + contact.getPhoneNumber());
                    System.out.println("  Email: " + contact.getEmail());
                    System.out.println("  Age: " + contact.getAge());
                },
                () -> System.out.println("Contact 'John Smith' not found")
            );
        System.out.println();

        System.out.println("5. STATISTICS:");
        System.out.println("Total contacts: " + phonebook.getContactCount());
        
        phonebook.getOldestContact().ifPresent(c -> 
            System.out.println("Oldest: " + c.getFirstName() + " " + c.getLastName() + 
                             " (Age: " + c.getAge() + ")")
        );
        
        phonebook.getYoungestContact().ifPresent(c -> 
            System.out.println("Youngest: " + c.getFirstName() + " " + c.getLastName() + 
                             " (Age: " + c.getAge() + ")")
        );

        System.out.println("\n6. REMOVE OPERATION:");
        boolean removed = phonebook.removeContact("555-0007");
        System.out.println("Contact 555-0007 removed: " + removed);
        System.out.println("New count: " + phonebook.getContactCount());
    }
}
