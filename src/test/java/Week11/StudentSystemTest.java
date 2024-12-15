package Week11;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class StudentSystemTest {

    @Test
    void testIfStudentIsPresent() throws Exception {
        StudentSystem system = new StudentSystem("src/main/java/Week11/students.csv");
        assertTrue(system.noOfStudents() > 0, "There should be more than one student in the file.");
    }

    @Test
    void testStudentWithId100() throws Exception {
        StudentSystem system = new StudentSystem("src/main/java/Week11/students.csv");
        assertFalse(system.getStudentById(100).isPresent(), "There is no student with id 100.");
    }

    @Test
    void testHighestGPAStudent() throws Exception {
        StudentSystem system = new StudentSystem("src/main/java/Week11/students.csv");
        assertEquals(9.8, system.getHighestGPAStudent().getGpa(), "The highest GPA is 9.8");
    }

    @Test
    void testExceptionForEmptyStudentList() {
        Exception exception = assertThrows(EmptyStudentListException.class, () -> {
            StudentSystem system = new StudentSystem("src/main/java/Week11/empty.csv");
            system.getHighestGPAStudent();
        });
    }

    @Test
    void testExceptionMessageForEmptyStudentList() {
        Exception exception = assertThrows(EmptyStudentListException.class, () -> {
            StudentSystem system = new StudentSystem("src/main/java/Week11/empty.csv");
            system.getHighestGPAStudent();
        });

        assertEquals("List of students is empty.", exception.getMessage(),
                "The message should be 'List of students is empty.'");
    }

    @Test
    void testNamesArray() throws Exception {
        List<Student> students = StudentSystem.readStudents("src/main/java/Week11/students.csv");
        List<String> names = students.stream()
                .limit(5)
                .map(Student::getName)
                .toList();
        assertIterableEquals(
                List.of("Camila Wood", "Alexander Thompson", "Liam Taylor", "Evelyn Jenkins", "Michael Jackson"),
                names,
                "The first five names should match.");
    }

    @Test
    void testSameStudent() throws Exception {
        StudentSystem system = new StudentSystem("src/main/java/Week11/students.csv");
        Student highestGPAStudent = system.getHighestGPAStudent();
        Optional<Student> studentWithId12 = system.getStudentById(12);
        assertTrue(studentWithId12.isPresent(), "There should be a student with id 12.");
        assertSame(highestGPAStudent, studentWithId12.get(), "It should be the same student.");
    }

    @Test
    void testValidateStudentDataThrowsExceptionForInvalidGPA() {
        InvalidStudentDataException exception = assertThrows(InvalidStudentDataException.class, () -> {
            List<Student> students = new ArrayList<>();
            students.add(new Student(1, "Invalid Student", "Uni A", "CS", 5.0));
            StudentSystem system = new StudentSystem(students);
        });
        assertEquals("Read data has invalid rows.", exception.getMessage());
    }

    @Test
    void testNumberOfStudentsMethodCoverage() throws Exception {
        StudentSystem system = new StudentSystem("src/main/java/Week11/students.csv");
        assertEquals(70, system.noOfStudents(), "The number of students should be 70.");
    }

    @Test
    void testStudentConstructorAndGetters() {
        Student student = new Student(1, "John Doe", "University A", "Computer Science", 3.8);

        assertEquals(1, student.getId());
        assertEquals("John Doe", student.getName());
        assertEquals("University A", student.getUniversity());
        assertEquals("Computer Science", student.getDepartment());
        assertEquals(3.8, student.getGpa(), 0.001);
    }

    @Test
    void testStudentSetters() {
        Student student = new Student(1, "John Doe", "University A", "Computer Science", 3.8);

        student.setId(2);
        student.setName("Jane Doe");
        student.setUniversity("University B");
        student.setDepartment("Electrical Engineering");
        student.setGpa(3.9);

        assertEquals(2, student.getId());
        assertEquals("Jane Doe", student.getName());
        assertEquals("University B", student.getUniversity());
        assertEquals("Electrical Engineering", student.getDepartment());
        assertEquals(3.9, student.getGpa(), 0.001);
    }

    @Test
    void testToString() {
        Student student = new Student(1, "John Doe", "University A", "Computer Science", 3.8);
        String expected = "Student{id='1', name='John Doe', university='University A', department='Computer Science', gpa=3.8}";
        assertEquals(expected, student.toString());
    }

    @Test
    void testLongestNameStudent() throws Exception {
        StudentSystem system = new StudentSystem("src/main/java/Week11/students.csv");
        assertEquals("Benjamin Richardson", system.getLongestNameStudent().getName(), "The student with longest name is " +
                "Benjamin Richardson");
    }
}