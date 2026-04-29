package Week1.day6;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Try-with-Resources for File Handling
 * 
 * Introduced in Java 7
 * Automatically closes resources (Autocloseable)
 * No manual finally required
 * Cleaner syntax and safer
 */
public class TryWithResources {

    
    
    static class ManualResourceManagement {
        
        public static void demonstrate() {
            System.out.println(" MANUAL \n");

           
            System.out.println("1. Manual closing :");
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader("test.txt"));
                String line = reader.readLine();
                System.out.println("Read line: " + (line != null ? line : "none"));
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            } finally {
                
                if (reader != null) {
                    try {
                        reader.close();
                        System.out.println(" Reader closed manually");
                    } catch (IOException e) {
                        System.out.println("Error closing: " + e.getMessage());
                    }
                }
            }

            System.out.println("\n2. Multiple resources");
            FileReader reader2 = null;
            BufferedWriter writer = null;
            try {
                reader2 = new FileReader("input.txt");
                writer = new BufferedWriter(new FileWriter("output.txt"));
                System.out.println("Resources opened");
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            } finally {
                // Multiple closes needed
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e) {
                    }
                }
                if (reader2 != null) {
                    try {
                        reader2.close();
                    } catch (IOException e) {
                    }
                }
            }

            System.out.println("\nProblems with manual closing:");
            System.out.println("   Easy to forget close()");
            System.out.println("  Verbose code");
            System.out.println("  Multiple try-catch blocks");
            System.out.println("  Resource leak risks");
        }
    }

  
    static class TryWithResourcesExamples {
        
        public static void demonstrate() {
            System.out.println(" \n");

         
            System.out.println("1. Single resource automatic closing:");
            try (BufferedReader reader = new BufferedReader(new FileReader("test.txt"))) {
                String line = reader.readLine();
                System.out.println("Read: " + (line != null ? line : "none"));
                System.out.println("✓ Reader auto-closed");
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }

            // 2. Multiple resources
            System.out.println("\n2. Multiple resources (all auto-closed):");
            try (
                FileReader reader = new FileReader("input.txt");
                BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))
            ) {
                System.out.println("Both resources opened");
                System.out.println(" Both auto-closed");
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }

            // 3. Many resources
            System.out.println("\n3. Many resources:");
            try (
                BufferedReader r1 = new BufferedReader(new FileReader("file1.txt"));
                BufferedReader r2 = new BufferedReader(new FileReader("file2.txt"));
                BufferedWriter w1 = new BufferedWriter(new FileWriter("output.txt"));
                BufferedWriter w2 = new BufferedWriter(new FileWriter("summary.txt"))
            ) {
                System.out.println("resources opened");
                System.out.println("All auto-closed in reverse order");
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    
    
    
    
    static class ExceptionHandling {
        
        public static void demonstrate() {
            System.out.println("\n EXCEPTION HANDLING \n");

           
            System.out.println("1. Exception in initialization:");
            try (BufferedReader reader = new BufferedReader(new FileReader("missing.txt"))) {
                String line = reader.readLine();
            } catch (FileNotFoundException e) {
                System.out.println("Caught: File not found");
            } catch (IOException e) {
                System.out.println("Caught: IO error");
            }

            System.out.println("\n2. Exception in try block:");
            try (BufferedReader reader = new BufferedReader(new StringReader("test"))) {
                String line = reader.readLine();
                int num = Integer.parseInt("invalid");  // Exception here
            } catch (NumberFormatException e) {
                System.out.println("Caught: Invalid number");
                System.out.println("Reader still closed");
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.println("\n3. Suppressed exceptions:");
            try (ResourceThatThrows resource = new ResourceThatThrows()) {
                throw new RuntimeException("Main exception");
            } catch (Exception e) {
                System.out.println("Caught: " + e.getMessage());
                if (e.getSuppressed().length > 0) {
                    System.out.println(" Suppressed: " + e.getSuppressed()[0].getMessage());
                }
            }
        }
    }

   
    
    static class CustomResource implements AutoCloseable {
        private String name;

        public CustomResource(String name) {
            this.name = name;
            System.out.println("  Opened: " + name);
        }

        public void doSomething() {
            System.out.println("  Using: " + name);
        }

        @Override
        public void close() {
            System.out.println("  Closed: " + name);
        }
    }

    static class ResourceThatThrows implements AutoCloseable {
        @Override
        public void close() throws Exception {
            throw new IOException("Error closing resource");
        }
    }

   
    
    static class CustomResourceExamples {
        
        public static void demonstrate() {
            System.out.println("\nCUSTOM AUTOCLOSEABLE RESOURCES \n");

          
            System.out.println("1. Custom resource:");
            try (CustomResource res = new CustomResource("MyResource")) {
                res.doSomething();
            }

            
            System.out.println("\n2. Multiple custom resources:");
            try (
                CustomResource r1 = new CustomResource("Resource1");
                CustomResource r2 = new CustomResource("Resource2");
                CustomResource r3 = new CustomResource("Resource3")
            ) {
                r1.doSomething();
                r2.doSomething();
                r3.doSomething();
            }
        }
    }

    
    
    static class SuppressedExceptionsExample {
        
        public static void demonstrate() {
            System.out.println("\nSUPPRESSED EXCEPTIONS \n");

            try (ResourceThatThrows resource = new ResourceThatThrows()) {
                throw new RuntimeException("Main operation failed");
            } catch (Exception e) {
                System.out.println("Main exception: " + e.getClass().getSimpleName());
                System.out.println("Message: " + e.getMessage());
                
                Throwable[] suppressed = e.getSuppressed();
                System.out.println("Suppressed exceptions: " + suppressed.length);
                for (Throwable t : suppressed) {
                    System.out.println("  - " + t.getClass().getSimpleName() + ": " + t.getMessage());
                }
            }
        }
    }

    
    

    
    
    static class StringReader extends Reader {
        private String content;
        private int position = 0;

        public StringReader(String content) {
            this.content = content;
        }

        @Override
        public int read(char[] cbuf, int off, int len) throws IOException {
            if (position >= content.length()) return -1;
            int count = Math.min(len, content.length() - position);
            content.getChars(position, position + count, cbuf, off);
            position += count;
            return count;
        }

        @Override
        public void close() throws IOException {
        
        }
    }


    
    public static void main(String[] args) {
        System.out.println("\n");

        ManualResourceManagement.demonstrate();
        TryWithResourcesExamples.demonstrate();
    
        ExceptionHandling.demonstrate();
        CustomResourceExamples.demonstrate();
        SuppressedExceptionsExample.demonstrate();
    

    
    }
}
