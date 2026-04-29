package Week1.CODE;


     
       public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "admin123";   // 👈 your password
        String hash = encoder.encode(rawPassword);

        System.out.println("HASH: " + hash);
    }
}
    
