package Week1.day2;

import java.util.*;

class Contact {
    private String name;
    private String surname;
    private String phoneNumber;

    public Contact(String name, String surname, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return name + " " + surname + ": " + phoneNumber;
    }
}

public class Phonebook {
  
    private Map<String, Contact> nameMap = new HashMap<>();
   
    private Map<String, Contact> phoneMap = new HashMap<>();
  
    private Set<Contact> sortedContacts = new TreeSet<>(Comparator.comparing(Contact::getSurname));

    public void addContact(String name, String surname, String phoneNumber) {
        Contact contact = new Contact(name, surname, phoneNumber);
        String fullName = name + " " + surname;
        nameMap.put(fullName, contact);
        phoneMap.put(phoneNumber, contact);
        sortedContacts.add(contact);
    }

    public Contact searchByName(String name) {
        return nameMap.get(name);
    }

    public Contact searchByNumber(String number) {
        return phoneMap.get(number);
    }

    public void listAllSorted() {
        for (Contact contact : sortedContacts) {
            System.out.println(contact);
        }
    }

    public static void main(String[] args) {
        Phonebook phonebook = new Phonebook();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Phonebook Menu:");
            System.out.println("1. Add Contact");
            System.out.println("2. Search by Name");
            System.out.println("3. Search by Number");
            System.out.println("4. List All Contacts (Sorted by Surname)");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter surname: ");
                    String surname = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phone = scanner.nextLine();
                    phonebook.addContact(name, surname, phone);
                    System.out.println("Contact added.");
                    break;
                case 2:
                    System.out.print("Enter full name (name surname): ");
                    String searchName = scanner.nextLine();
                    Contact foundByName = phonebook.searchByName(searchName);
                    if (foundByName != null) {
                        System.out.println("Found: " + foundByName);
                    } else {
                        System.out.println("Contact not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter phone number: ");
                    String searchPhone = scanner.nextLine();
                    Contact foundByPhone = phonebook.searchByNumber(searchPhone);
                    if (foundByPhone != null) {
                        System.out.println("Found: " + foundByPhone);
                    } else {
                        System.out.println("Contact not found.");
                    }
                    break;
                case 4:
                    System.out.println("All contacts sorted by surname:");
                    phonebook.listAllSorted();
                    break;
                case 5:
                    System.out.println("Exiting");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}