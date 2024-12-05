package Week7;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

record EmployeeRecord(String name, String position, double salary, LocalDate employmentDate) {

    public static EmployeeRecord createIntern(String name) {
        return new EmployeeRecord(name, "Intern", 30000, LocalDate.now());
    }

    public boolean hasBeenWithCompanyForOverFiveYears() {
        long yearsWorked = ChronoUnit.YEARS.between(employmentDate, LocalDate.now());
        return yearsWorked > 5;
    }

    public static double calculateAverageSalary(List<EmployeeRecord> employees, String department) {
        return employees.stream()
                .filter(employee -> employee.position().equalsIgnoreCase(department))
                .mapToDouble(EmployeeRecord::salary)
                .average()
                .orElse(0);
    }

    public static void findAndPrintEmployeesInRole(List<EmployeeRecord> employees, String role) {
        employees.stream()
                .filter(employee -> employee.position().equalsIgnoreCase(role))
                .forEach(employee -> System.out.println(employee));
    }
}

class EmployeeTest {
    public static void main(String[] args) {
        List<EmployeeRecord> employees = new ArrayList<>();

        employees.add(new EmployeeRecord("John Doe", "Senior Developer", 95000, LocalDate.of(2015, 5, 1)));
        employees.add(new EmployeeRecord("Jane Smith", "Junior Developer", 65000, LocalDate.of(2020, 8, 15)));
        employees.add(new EmployeeRecord("Bob White", "Intern", 30000, LocalDate.now()));

        EmployeeRecord intern = EmployeeRecord.createIntern("Sam Green");
        employees.add(intern);

        System.out.println("Employees with over 5 years:");
        employees.stream()
                .filter(EmployeeRecord::hasBeenWithCompanyForOverFiveYears)
                .forEach(employee -> System.out.println(employee));

        double avgSalary = EmployeeRecord.calculateAverageSalary(employees, "Senior Developer");
        System.out.println("\nAverage salary of Senior Developers: $" + avgSalary);

        System.out.println("\nSenior Developers:");
        EmployeeRecord.findAndPrintEmployeesInRole(employees, "Senior Developer");
    }
}
