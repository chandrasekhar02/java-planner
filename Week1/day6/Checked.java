package Week1.day6;

import java.io.*;
import java.util.*;

/**
 * Checked vs Unchecked Exceptions
 * 
 * CHECKED: Must be caught or declared
 *          IOException, SQLException, etc.
 *          Compile error if not handled
 * 
 * UNCHECKED: Can be ignored
 *            RuntimeException subclasses
 *            NullPointerException, ArithmeticException, etc.
 *            No compile error if not handled
 */
public class Checked {

    
    static class CheckedExceptionExamples {
        
        public static void demonstrate() throws IOException {
            System.out.println(" CHECKED EXCEPTIONS \n");

        
            System.out.println("1. IOException (Checked):");
            try {
                FileReader reader = new FileReader("nonexistent.txt");
       
            } catch (FileNotFoundException e) {
                System.out.println("Caught: " + e.getClass().getSimpleName());
            }

            System.out.println("\n2. Custom checked exception:");
            try {
                readConfigFile("config.txt");
            } catch (ConfigurationException e) {
                System.out.println("Caught: " + e.getMessage());
            }

     
            System.out.println("\n3. Multiple checked exceptions:");
            try {
                processFile("data.txt");
            } catch (FileNotFoundException | ConfigurationException e) {
                System.out.println(" Caught: " + e.getClass().getSimpleName());
            }
        }

      
        static void readConfigFile(String filename) throws ConfigurationException {
            throw new ConfigurationException("Config file not found: " + filename);
        }

        static void processFile(String filename) throws FileNotFoundException, ConfigurationException {
            throw new FileNotFoundException("File: " + filename);
        }
    }

    
    static class UncheckedException {
        
        public static void demonstrate() {
            System.out.println("\nUNCHECKED EXCEPTIONS \n");

           
            System.out.println("1. NullPointerException:");
            try {
                String text = null;
                int length = text.length();  // Will throw NPE
            } catch (NullPointerException e) {
                System.out.println("✓ Caught: " + e.getClass().getSimpleName());
            }

            System.out.println("\n2. ArithmeticException (Unchecked):");
            try {
                int result = 10 / 0;
            } catch (ArithmeticException e) {
                System.out.println(" Caught: Division by zero");
            }

            System.out.println("\n3. ArrayIndexOutOfBoundsException (Unchecked):");
            try {
                int[] arr = {1, 2, 3};
                int val = arr[10];
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(" Caught: Index out of bounds");
            }

            System.out.println("\n4. ClassCastException ");
            try {
                Object obj = "String";
                Integer num = (Integer) obj;
            } catch (ClassCastException e) {
                System.out.println(" Caught: Invalid cast");
            }

            demonstrateIgnoredException();
        }

        static void demonstrateIgnoredException() {
            System.out.println("\n5. Unchecked can be ignored (compiles fine):");
            int result = 10 / 0;  
        }
    }

   
    


    
    static class Practical {
        
        public static void demonstrate() {
            System.out.println("\n");

            System.out.println("1. Checked - FileNotFoundException:");
            try {
      
                readFile("missing.txt");
            } catch (ConfigurationException e) {
                System.out.println("   Handled: " + e.getMessage());
            }

            System.out.println("\n2. Unchecked ");
            try {
         
                String str = null;
                System.out.println(str.length());
            } catch (NullPointerException e) {
                System.out.println("   Handled: null reference");
            }

        
        }

        static void readFile(String filename) throws ConfigurationException {
            throw new ConfigurationException("File not found: " + filename);
        }
    }

  

   
    
    static class ConfigurationException extends Exception {
        public ConfigurationException(String message) {
            super(message);
        }

        public ConfigurationException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    
    public static void main(String[] args) throws IOException {
        System.out.println("\n");

        CheckedExceptionExamples.demonstrate();
     

    }
}
