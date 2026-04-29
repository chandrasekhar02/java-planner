package Week1.Day3;

import java.util.*;

/**
 * Optional in Java: Proper Usage to Avoid Null Checks
 * 
 * Optional<T>: A container that may or may not contain a non-null value
 * Introduced in Java 8 to reduce NullPointerException and avoid null checks
 * 
 * NOT a replacement for null, but a better way to handle missing values
 */
public class OptionalUsage {

    static class User {
        private String name;
        private String email;
        private Integer age;
        private String phone;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        @Override
        public String toString() {
            return String.format("User{name='%s', email='%s', age=%s, phone=%s}", 
                name, email, age, phone);
        }
    }

    static class UserDatabase {
        private Map<String, User> users = new HashMap<>();

        public UserDatabase() {
            // Sample data
            User user1 = new User("Alice", "alice@example.com");
            user1.setAge(25);
            user1.setPhone("123-456-7890");
            users.put("alice", user1);

            User user2 = new User("Bob", "bob@example.com");
            user2.setAge(30);
            users.put("bob", user2);

            users.put("charlie", new User("Charlie", "charlie@example.com"));
        }

      
        public User findUserOldWay(String username) {
            return users.get(username);
        }

       
        public Optional<User> findUser(String username) {
            return Optional.ofNullable(users.get(username));
        }

        
        public Optional<String> getUserPhone(String username) {
            return findUser(username)
                .flatMap(user -> Optional.ofNullable(user.getPhone()));
        }

        
        public Optional<Integer> getUserAge(String username) {
            return findUser(username)
                .map(User::getAge);
        }

        public boolean userHasEmail(String username) {
            return findUser(username)
                .map(User::getEmail)
                .isPresent();
        }

   
        public String getUserEmailOrDefault(String username) {
            return findUser(username)
                .map(User::getEmail)
                .orElse("no-email@example.com");
        }

  
        public String getUserPhoneOrCompute(String username) {
            return findUser(username)
                .map(User::getPhone)
                .orElseGet(() -> "Phone not available for " + username);
        }

        public User getUserOrThrow(String username) {
            return findUser(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + username));
        }

        public void printUserAge(String username) {
            findUser(username)
                .ifPresent(user -> {
                    if (user.getAge() != null) {
                        System.out.println(user.getName() + " is " + user.getAge() + " years old");
                    }
                });
        }

        
        public void notifyUser(String username) {
            findUser(username)
                .ifPresentOrElse(
                    user -> System.out.println("Notification sent to " + user.getEmail()),
                    () -> System.out.println("User not found, cannot send notification")
                );
        }
    }

  
    static class OptionalMethods {

      
        static void creatingOptionals() {
            System.out.println("\nCreating Optional");

         
            Optional<String> empty = Optional.empty();
            System.out.println("Empty: " + empty);

        
            Optional<String> present = Optional.of("Hello");
            System.out.println("Of: " + present);

       
            String value = null;
            Optional<String> nullable = Optional.ofNullable(value);
            System.out.println("OfNullable (null): " + nullable);

            nullable = Optional.ofNullable("World");
            System.out.println("OfNullable (value): " + nullable);
        }

        static void checkingPresence() {
            System.out.println("\n Checking ");

            Optional<String> opt = Optional.of("Hello");

            
            if (opt.isPresent()) {
                System.out.println("Value is present: " + opt.get());
            }

            
            if (!opt.isEmpty()) {
                System.out.println("Value is not empty");
            }

       
            Optional<String> empty = Optional.empty();
            System.out.println("Empty isPresent: " + empty.isPresent());
            System.out.println("Empty get (would throw): " + (empty.isEmpty() ? "EMPTY" : empty.get()));
        }

       
        static void gettingValues() {
            System.out.println("\n Getting Values");

            Optional<String> present = Optional.of("Present");
            Optional<String> empty = Optional.empty();

            try {
                System.out.println("get() from present: " + present.get());
            } catch (NoSuchElementException e) {
                System.out.println("Exception: " + e);
            }

           
            System.out.println("orElse() from empty: " + empty.orElse("DEFAULT"));
            System.out.println("orElse() from present: " + present.orElse("DEFAULT"));

          
            System.out.println("orElseGet() from empty: " + 
                empty.orElseGet(() -> "COMPUTED_" + System.currentTimeMillis()));
        }

        static void mappingOptionals() {
            System.out.println("\nMapping Optional");

            Optional<String> name = Optional.of("Alice");

          
            Optional<Integer> length = name.map(String::length);
            System.out.println("map() length: " + length);

            Optional<String> empty = Optional.empty();
            Optional<Integer> emptyLength = empty.map(String::length);
            System.out.println("map() on empty: " + emptyLength);

    
            Optional<String> str = Optional.of("hello");
            Optional<Integer> firstChar = str.flatMap(s -> 
                Optional.of(s.charAt(0) - 'a')
            );
            System.out.println("flatMap(): " + firstChar);
        }

        
        static void filteringOptionals() {
            System.out.println("\n--- Filtering Optionals ---");

            Optional<Integer> age1 = Optional.of(25);
            Optional<Integer> age2 = Optional.of(5);

            Optional<Integer> adult1 = age1.filter(a -> a >= 18);
            Optional<Integer> adult2 = age2.filter(a -> a >= 18);

            System.out.println("filter(>= 18) age 25: " + adult1);
            System.out.println("filter(>= 18) age 5: " + adult2);
        }

  
        static void actions() {
            System.out.println("\nPerforming Actions ");

            Optional<String> present = Optional.of("Hello");
            Optional<String> empty = Optional.empty();

         
            present.ifPresent(v -> System.out.println("Present: " + v));
            empty.ifPresent(v -> System.out.println("This won't print"));

     
            present.ifPresentOrElse(
                v -> System.out.println("Value: " + v),
                () -> System.out.println("No value")
            );

            empty.ifPresentOrElse(
                v -> System.out.println("Value: " + v),
                () -> System.out.println("No value (empty)")
            );
        }

        static void streamConversion() {
            System.out.println("\n Stream Conversion ");

            List<Optional<String>> optionals = Arrays.asList(
                Optional.of("A"),
                Optional.empty(),
                Optional.of("B"),
                Optional.empty(),
                Optional.of("C")
            );

            System.out.println("Original optionals: " + optionals);

           
            optionals.stream()
                .flatMap(Optional::stream)
                .forEach(System.out::println);
        }
    }

    public static void main(String[] args) {

        UserDatabase db = new UserDatabase();

        System.out.println("USER DATABASE \n");

     
        System.out.println("1. OLD WAY ");
        User userOld = db.findUserOldWay("alice");
        if (userOld != null) {
            System.out.println("Found: " + userOld);
            if (userOld.getPhone() != null) {
                System.out.println("Phone: " + userOld.getPhone());
            } else {
                System.out.println("Phone: Not available");
            }
        } else {
            System.out.println("User not found");
        }
        System.out.println();

      
        System.out.println("2. NEW WAY");
        
        // Finding user
        System.out.println("\nFinding 'alice':");
        db.findUser("alice").ifPresent(u -> System.out.println("Found: " + u));
        
        System.out.println("\nFinding 'unknown':");
        db.findUser("unknown")
            .ifPresentOrElse(
                u -> System.out.println("Found: " + u),
                () -> System.out.println("User not found")
            );
        System.out.println();

      
        System.out.println("3. OPTIONAL OPERATIONS:");
        
        System.out.println("\nGet user phone (with orElse):");
        String phone = db.getUserPhone("alice").orElse("No phone");
        System.out.println("Alice's phone: " + phone);
        
        System.out.println("\nGet phone with orElseGet:");
        String bobPhone = db.getUserPhone("bob")
            .orElseGet(() -> "Phone not registered for Bob");
        System.out.println("Bob's phone: " + bobPhone);
        
        System.out.println("\nGet age (map):");
        db.getUserAge("alice")
            .ifPresent(age -> System.out.println("Alice's age: " + age));
        
        System.out.println("\nGet all user info:");
        System.out.println("Alice: " + db.findUser("alice"));
        System.out.println("Bob: " + db.findUser("bob"));
        System.out.println("Charlie: " + db.findUser("charlie"));
        System.out.println();

        OptionalMethods.creatingOptionals();
        OptionalMethods.checkingPresence();
        OptionalMethods.gettingValues();
        OptionalMethods.mappingOptionals();
        OptionalMethods.filteringOptionals();
        OptionalMethods.actions();
    
    }
}
