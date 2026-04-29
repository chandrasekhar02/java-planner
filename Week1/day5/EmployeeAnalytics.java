package Week1.day5;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeAnalytics {

    static class Employee {
        private String name;
        private String department;
        private double salary;
        private int yearsExperience;

        public Employee(String name, String department, double salary, int yearsExperience) {
            this.name = name;
            this.department = department;
            this.salary = salary;
            this.yearsExperience = yearsExperience;
        }

        public String getName() { return name; }
        public String getDepartment() { return department; }
        public double getSalary() { return salary; }
        public int getYearsExperience() { return yearsExperience; }

        @Override
        public String toString() {
            return String.format("%-20s $%-10.2f (%d yrs)", name, salary, yearsExperience);
        }
    }

    static class EmployeeDataGenerator {
        static List<Employee> generateEmployees() {
            List<Employee> employees = new ArrayList<>();
            String[] departments = {"Engineering", "Sales", "HR", "Marketing", "Finance"};
            String[] firstNames = {"John", "Jane", "Bob", "Alice", "Charlie", "Diana", "Eve", "Frank"};
            String[] lastNames = {"Smith", "Johnson", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor"};

            Random random = new Random(42);

            for (int i = 0; i < 100; i++) {
                for (String dept : departments) {
                    String firstName = firstNames[random.nextInt(firstNames.length)];
                    String lastName = lastNames[random.nextInt(lastNames.length)];
                    String name = firstName + " " + lastName + i;
                    double salary = 40000 + random.nextDouble() * 100000;
                    int years = random.nextInt(20) + 1;

                    employees.add(new Employee(name, dept, salary, years));
                }
            }

            return employees;
        }
    }

    static class AnalysisOperations {

        static void groupByDepartment(List<Employee> employees) {
            System.out.println("\n GROUP BY DEPARTMENT\n");

            Map<String, List<Employee>> byDept = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

            byDept.forEach((dept, empList) ->
                System.out.println(dept + ": " + empList.size() + " employees")
            );
        }

        static void averageSalaryByDepartment(List<Employee> employees) {
            System.out.println("\n 2. AVERAGE SALARY BY DEPARTMENT\n");

            Map<String, Double> avgSalary = employees.stream()
                .collect(Collectors.groupingBy(
                    Employee::getDepartment,
                    Collectors.averagingDouble(Employee::getSalary)
                ));

            avgSalary.forEach((dept, avgSal) ->
                System.out.printf("%s: $%.2f%n", dept, avgSal)
            );
        }

        static void top3EarnersByDepartment(List<Employee> employees) {
            System.out.println("\n===== 3. TOP 3 EARNERS PER DEPARTMENT =====\n");

            Map<String, List<Employee>> topEarners = employees.stream()
                .collect(Collectors.groupingBy(
                    Employee::getDepartment,
                    Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> list.stream()
                            .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                            .limit(3)
                            .collect(Collectors.toList())
                    )
                ));

            topEarners.forEach((dept, empList) -> {
                System.out.println(dept + ":");
                empList.forEach(e -> System.out.println("  " + e));
            });
        }

        static void comprehensiveAnalysis(List<Employee> employees) {
            System.out.println("\n 4. COMPREHENSIVE ANALYSIS \n");

            Map<String, Map<String, Object>> analysis = employees.stream()
                .collect(Collectors.groupingBy(
                    Employee::getDepartment,
                    Collectors.collectingAndThen(
                        Collectors.toList(),
                        empList -> {
                            Map<String, Object> dept = new LinkedHashMap<>();

                            dept.put("count", empList.size());

                            double avgSalary = empList.stream()
                                .mapToDouble(Employee::getSalary)
                                .average()
                                .orElse(0);
                            dept.put("avgSalary", avgSalary);

                            double minSalary = empList.stream()
                                .mapToDouble(Employee::getSalary)
                                .min()
                                .orElse(0);
                            double maxSalary = empList.stream()
                                .mapToDouble(Employee::getSalary)
                                .max()
                                .orElse(0);
                            dept.put("minSalary", minSalary);
                            dept.put("maxSalary", maxSalary);

                            List<Employee> top3 = empList.stream()
                                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                                .limit(3)
                                .collect(Collectors.toList());
                            dept.put("top3", top3);

                            return dept;
                        }
                    )
                ));

            analysis.forEach((dept, stats) -> {
                System.out.println("DEPARTMENT: " + dept);
                System.out.printf("  Count: %d%n", (Integer) stats.get("count"));
                System.out.printf("  Average Salary: $%.2f%n", (Double) stats.get("avgSalary"));
                System.out.printf("  Min Salary: $%.2f%n", (Double) stats.get("minSalary"));
                System.out.printf("  Max Salary: $%.2f%n", (Double) stats.get("maxSalary"));
                System.out.println("  Top 3 Earners:");
                @SuppressWarnings("unchecked")
                List<Employee> top3 = (List<Employee>) stats.get("top3");
                top3.forEach(e -> System.out.println("    " + e));
                System.out.println();
            });
        }

        static void departmentStatistics(List<Employee> employees) {
            System.out.println("\n5. DEPARTMENT STATISTICS\n");

            Map<String, DoubleSummaryStatistics> stats = employees.stream()
                .collect(Collectors.groupingBy(
                    Employee::getDepartment,
                    Collectors.summarizingDouble(Employee::getSalary)
                ));

            stats.forEach((dept, stat) -> {
                System.out.println(dept + ":");
                System.out.printf("  Count: %d%n", stat.getCount());
                System.out.printf("  Average: $%.2f%n", stat.getAverage());
                System.out.printf("  Min: $%.2f%n", stat.getMin());
                System.out.printf("  Max: $%.2f%n", stat.getMax());
                System.out.printf("  Total: $%.2f%n", stat.getSum());
            });
        }

        static void topEarnerNames(List<Employee> employees) {
            System.out.println("\n6. TOP EARNER NAMES PER DEPARTMENT \n");

            Map<String, String> topEarnersByDept = employees.stream()
                .collect(Collectors.groupingBy(
                    Employee::getDepartment,
                    Collectors.collectingAndThen(
                        Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)),
                        opt -> opt.map(Employee::getName).orElse("N/A")
                    )
                ));

            topEarnersByDept.forEach((dept, name) ->
                System.out.println(dept + ": " + name)
            );
        }

        static void aboveAverageSalaries(List<Employee> employees) {
            System.out.println("\n 7. ABOVE-AVERAGE EARNERS PER DEPARTMENT\n");

            Map<String, List<Employee>> aboveAvg = employees.stream()
                .collect(Collectors.groupingBy(
                    Employee::getDepartment,
                    Collectors.collectingAndThen(
                        Collectors.toList(),
                        empList -> {
                            double avg = empList.stream()
                                .mapToDouble(Employee::getSalary)
                                .average()
                                .orElse(0);
                            return empList.stream()
                                .filter(e -> e.getSalary() > avg)
                                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                                .collect(Collectors.toList());
                        }
                    )
                ));

            aboveAvg.forEach((dept, empList) -> {
                System.out.println(dept + " (" + empList.size() + " above average):");
                empList.forEach(e -> System.out.println("  " + e));
            });
        }
    }

    public static void main(String[] args) {
        System.out.println("EMPLOYEE ANALYTICS: STREAM PIPELINE ");
        System.out.println("(Generated 500 employees across 5 departments)\n");

        List<Employee> employees = EmployeeDataGenerator.generateEmployees();
        System.out.println("Total employees: " + employees.size());

        AnalysisOperations.groupByDepartment(employees);
        AnalysisOperations.averageSalaryByDepartment(employees);
        AnalysisOperations.top3EarnersByDepartment(employees);
        AnalysisOperations.topEarnerNames(employees);
        AnalysisOperations.departmentStatistics(employees);
        AnalysisOperations.aboveAverageSalaries(employees);
        AnalysisOperations.comprehensiveAnalysis(employees);

    }
}
