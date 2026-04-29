package Week1.day6;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * CSV File Parser with Exception Handling
 * 
 * Task: Read employee CSV, handle errors, write summary report
 * Features:
 * - Custom exception hierarchy
 * - Try-with-resources for file handling
 * - CSV parsing with validation
 * - Summary report generation
 */
public class CSVFileParser {

 

    static class CSVParsingException extends Exception {
        public CSVParsingException(String message) {
            super(message);
        }

        public CSVParsingException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    static class MalformedRowException extends CSVParsingException {
        private int lineNumber;
        private String rowData;

        public MalformedRowException(String message, int lineNumber, String rowData) {
            super(message);
            this.lineNumber = lineNumber;
            this.rowData = rowData;
        }

        public int getLineNumber() {
            return lineNumber;
        }

        public String getRowData() {
            return rowData;
        }
    }

    static class InvalidDataException extends CSVParsingException {
        private String field;
        private String value;

        public InvalidDataException(String message, String field, String value) {
            super(message);
            this.field = field;
            this.value = value;
        }

        public String getField() {
            return field;
        }

        public String getValue() {
            return value;
        }
    }

   

    static class Employee {
        private String employeeId;
        private String name;
        private String department;
        private double salary;

        public Employee(String employeeId, String name, String department, double salary) {
            this.employeeId = employeeId;
            this.name = name;
            this.department = department;
            this.salary = salary;
        }

        public String getEmployeeId() { return employeeId; }
        public String getName() { return name; }
        public String getDepartment() { return department; }
        public double getSalary() { return salary; }

        @Override
        public String toString() {
            return String.format("%-6s | %-20s | %-12s | $%10.2f", 
                employeeId, name, department, salary);
        }
    }

   

    static class EmployeeCSVParser {
        private static final String DELIMITER = ",";

       
        public static List<Employee> parseCSV(Path csvFile) throws CSVParsingException {
            List<Employee> employees = new ArrayList<>();
            List<String> errors = new ArrayList<>();
            int lineNumber = 0;

            try (BufferedReader reader = Files.newBufferedReader(csvFile)) {
                String line;
                lineNumber = 1; 
                
               
                reader.readLine();
                lineNumber++;

                while ((line = reader.readLine()) != null) {
                    try {
                        if (line.trim().isEmpty()) {
                            lineNumber++;
                            continue;
                        }

                        Employee emp = parseRow(line, lineNumber);
                        employees.add(emp);
                    } catch (MalformedRowException | InvalidDataException e) {
                        String error = "Line " + lineNumber + ": " + e.getMessage();
                        if (e instanceof MalformedRowException) {
                            error += " [" + ((MalformedRowException) e).getRowData() + "]";
                        }
                        errors.add(error);
                    }
                    lineNumber++;
                }

                if (!errors.isEmpty()) {
                    System.err.println("\nPARSING WARNINGS:");
                    errors.forEach(System.err::println);
                    System.err.println();
                }

            } catch (IOException e) {
                throw new CSVParsingException("Failed to read CSV file: " + csvFile, e);
            }

            if (employees.isEmpty()) {
                throw new CSVParsingException("No valid employees found in CSV");
            }

            return employees;
        }

    
        private static Employee parseRow(String line, int lineNumber) 
                throws MalformedRowException, InvalidDataException {
            String[] fields = line.split(DELIMITER);

            if (fields.length != 4) {
                throw new MalformedRowException(
                    "Expected 4 fields, got " + fields.length,
                    lineNumber, line
                );
            }

            String employeeId = fields[0].trim();
            String name = fields[1].trim();
            String department = fields[2].trim();
            String salaryStr = fields[3].trim();

        
            if (employeeId.isEmpty()) {
                throw new InvalidDataException("Employee ID cannot be empty", "employeeId", "");
            }

            if (name.isEmpty()) {
                throw new InvalidDataException("Name cannot be empty", "name", "");
            }

            if (department.isEmpty()) {
                throw new InvalidDataException("Department cannot be empty", "department", "");
            }

            double salary;
            try {
                salary = Double.parseDouble(salaryStr);
                if (salary < 0) {
                    throw new InvalidDataException("Salary cannot be negative", "salary", salaryStr);
                }
            } catch (NumberFormatException e) {
                throw new InvalidDataException("Invalid salary format: " + salaryStr, "salary", salaryStr);
            }

            return new Employee(employeeId, name, department, salary);
        }
    }


    static class TestCSVGenerator {
        
        public static void createTestCSV(Path path) throws IOException {
            String csv = """
EmpID,Name,Department,Salary
E001,John Smith,Engineering,85000.50
E002,Jane Doe,Sales,65000.00
E003,Bob Johnson,Engineering,92000.75
,Missing Name,HR,55000.00
E005,Alice Williams,Marketing,68000.00
E006,Charlie Brown,Sales,invalid
E007,Diana Davis,Finance,78000.00
E008,,Engineering,81000.00
E009,Eve Miller,Sales,72000.00
E010,Frank Wilson,Engineering,-50000.00
""";
            Files.writeString(path, csv, StandardCharsets.UTF_8);
        }
    }

   

    static class ReportGenerator {
        
        public static void generateReport(List<Employee> employees, Path reportFile) 
                throws IOException {
            try (BufferedWriter writer = Files.newBufferedWriter(reportFile)) {
                // Header
                writer.write("EMPLOYEE ANALYTICS REPORT\n");
                writer.write("=".repeat(80) + "\n\n");

      
                writer.write("SUMMARY STATISTICS\n");
                writer.write("-".repeat(80) + "\n");
                writer.write(String.format("Total Employees: %d%n", employees.size()));

                double avgSalary = employees.stream()
                    .mapToDouble(Employee::getSalary)
                    .average()
                    .orElse(0);
                writer.write(String.format("Average Salary: $%.2f%n", avgSalary));

                double minSalary = employees.stream()
                    .mapToDouble(Employee::getSalary)
                    .min()
                    .orElse(0);
                double maxSalary = employees.stream()
                    .mapToDouble(Employee::getSalary)
                    .max()
                    .orElse(0);
                writer.write(String.format("Min Salary: $%.2f%n", minSalary));
                writer.write(String.format("Max Salary: $%.2f%n\n", maxSalary));

              
                writer.write("BY DEPARTMENT\n");
                writer.write("-".repeat(80) + "\n");
                Map<String, List<Employee>> byDept = employees.stream()
                    .collect(Collectors.groupingBy(Employee::getDepartment));

                byDept.forEach((dept, empList) -> {
                    try {
                        double deptAvg = empList.stream()
                            .mapToDouble(Employee::getSalary)
                            .average()
                            .orElse(0);
                        writer.write(String.format("%s: %d employees, Avg: $%.2f%n",
                            dept, empList.size(), deptAvg));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

                writer.write("\n");
                writer.write("ALL EMPLOYEES\n");
                writer.write("-".repeat(80) + "\n");
                writer.write(String.format("%-6s | %-20s | %-12s | %s%n",
                    "EmpID", "Name", "Department", "Salary"));
                writer.write("-".repeat(80) + "\n");

                employees.forEach(emp -> {
                    try {
                        writer.write(emp.toString() + "\n");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

                writer.write("\n Report generated successfully");
            }
        }
    }
    
    public static void main(String[] args) {
        System.out.println("CSV FILE PARSER WITH EXCEPTION HANDLING \n");

        Path csvFile = Paths.get("employees.csv");
        Path reportFile = Paths.get("report.txt");

        try {
         
            System.out.println("1. Creating test CSV file...");
            TestCSVGenerator.createTestCSV(csvFile);
            System.out.println(" CSV file created\n");

            // Parse CSV with error handling
            System.out.println("2. Parsing CSV file...");
            List<Employee> employees = EmployeeCSVParser.parseCSV(csvFile);
            System.out.println(" Successfully parsed " + employees.size() + " employees\n");

            System.out.println("3. Generating report...");
            ReportGenerator.generateReport(employees, reportFile);
            System.out.println(" Report written to: " + reportFile.toAbsolutePath() + "\n");

            // Display report
            System.out.println("4. Report contents:\n");
            System.out.println(Files.readString(reportFile));

        } catch (CSVParsingException e) {
            System.err.println("CSV Parsing Error: " + e.getMessage());
            if (e.getCause() != null) {
                System.err.println("Caused by: " + e.getCause().getMessage());
            }
        } catch (IOException e) {
            System.err.println("IO Error: " + e.getMessage());
        } finally {
           
            try {
                Files.deleteIfExists(csvFile);
                Files.deleteIfExists(reportFile);
                System.out.println("\n Temporary files cleaned up");
            } catch (IOException e) {
                System.err.println("Cleanup failed: " + e.getMessage());
            }
        }

    }
}
