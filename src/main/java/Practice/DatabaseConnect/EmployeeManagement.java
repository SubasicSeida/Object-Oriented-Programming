package Practice.DatabaseConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

enum Department {
    IT,
    HR,
    SALES,
    FINANCE
}

class Employee {
    private int id;
    private String name;
    private Department department;
    private double salary;

    public Employee(int id, String name, Department department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", department=" + department +
                ", salary=" + salary +
                '}';
    }
}

class EmployeeManagement {
    List<Employee> employees = new ArrayList<>();

    public EmployeeManagement(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> getEmployeesByDepartment(Department department){
        return employees.stream()
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toList());
    }

    public List<Employee> getEmployeesWithGreaterSalary(double salary){
        return employees.stream()
                .filter(employee -> employee.getSalary() >= salary)
                .collect(Collectors.toList());
    }

    public void printEmployeeNamesReverse(){
        List<Employee> reversed = new ArrayList<>(employees);
        Collections.reverse(reversed);
        reversed.forEach(employee -> System.out.println(employee.getName()));
    }
}

class EmployeeDb {
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/tasksdb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "hFW5yLylRpx!=2)";

    private Connection connection = null;

    public EmployeeDb() {
        try {
            connection = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection(){
        return connection;
    }

    public void createEmployee(Employee employee){
        String query = "INSERT INTO employees (id, name, department, salary)" +
                "VALUES (?, ?, ?, ?)";
        try(PreparedStatement pstmt = connection.prepareStatement(query)){

            pstmt.setInt(1, employee.getId());
            pstmt.setString(2, employee.getName());
            pstmt.setString(3, employee.getDepartment().name());
            pstmt.setDouble(4, employee.getSalary());
            pstmt.executeUpdate();

        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void updateEmployeeSalary(int id, double salary){
        String query = "UPDATE employees SET salary = ? WHERE id = ?";

        try(PreparedStatement pstmt = connection.prepareStatement(query)){

            pstmt.setDouble(1, salary);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();

        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void removeEmployee(int id){
        String query = "DELETE FROM employees WHERE id = ?";

        try(PreparedStatement pstmt = connection.prepareStatement(query)){

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Employee employee1 = new Employee(1, "Alice", Department.IT, 75000);
        Employee employee2 = new Employee(2, "Bob", Department.HR, 50000);
        Employee employee3 = new Employee(3, "Charlie", Department.SALES, 60000);

        EmployeeDb employeeDb = new EmployeeDb();

        employeeDb.createEmployee(employee1);
        employeeDb.createEmployee(employee2);
        employeeDb.createEmployee(employee3);

        EmployeeManagement employeeManagement = new EmployeeManagement(
                List.of(employee1, employee2, employee3));

        System.out.println(employeeManagement.getEmployeesByDepartment(Department.IT));
        System.out.println(employeeManagement.getEmployeesWithGreaterSalary(55000));

        employeeDb.updateEmployeeSalary(2, 52000);
        employeeDb.removeEmployee(3);
    }
}
