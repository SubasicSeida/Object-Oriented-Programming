package Week11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class Student {
    private int id;
    private String name;
    private String university;
    private String department;
    private double gpa;

    public Student(int id, String name, String university, String department, double gpa){
        this.id = id;
        this.name = name;
        this.university = university;
        this.department = department;
        this.gpa = gpa;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
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

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", university='" + university + '\'' +
                ", department='" + department + '\'' +
                ", gpa=" + gpa +
                '}';
    }
}

class StudentSystem {
    private List<Student> students;

    public StudentSystem(String filename) throws IOException, InvalidStudentDataException{
        List<Student> studentsFromFile = new ArrayList<>();
        studentsFromFile = readStudents(filename);
        validateStudentData(studentsFromFile);
        this.students = studentsFromFile;
    }

    public StudentSystem(List<Student> students) throws InvalidStudentDataException {
        validateStudentData(students);
        this.students = students;
    }

    public static List<Student> readStudents(String filename) throws IOException {
        List<Student> students = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String line;
            while((line = reader.readLine()) != null) {
                String[] studentData = line.split(",");
                int id = Integer.parseInt(studentData[0]);
                String name = studentData[1];
                String university = studentData[2];
                String department = studentData[3];
                double gpa = Double.parseDouble(studentData[4]);
                students.add(new Student(id, name, university, department, gpa));
            }
            reader.close();
        }
        return students;
    }

    public int noOfStudents() {
        return students.size();
    }

    public Optional<Student> getStudentById(int id) {
        for(Student student : students) {
            if(student.getId() == id){
                return Optional.of(student);
            }
        }
        return Optional.empty();
    }

    public Student getHighestGPAStudent() {
        if(students.isEmpty()){
            throw new EmptyStudentListException("List of students is empty.");
        }
        Student highestGPAStudent = students.get(0);
        for(Student student : students) {
            if(student.getGpa() > highestGPAStudent.getGpa()){
                highestGPAStudent = student;
            }
        }
        return highestGPAStudent;
    }

    public Student getLongestNameStudent() {
        if(students.isEmpty()){
            throw new EmptyStudentListException("List of students is empty.");
        }
        Student longestNameStudent = students.get(0);
        for(Student student : students) {
            if(student.getName().length() > longestNameStudent.getName().length()){
                longestNameStudent = student;
            }
        }
        return longestNameStudent;
    }

    private void validateStudentData(List<Student> students) throws InvalidStudentDataException {
        for(Student student : students){
            if(student.getGpa() < 6 || student.getGpa() > 10){
                throw new InvalidStudentDataException("Read data has invalid rows.");
            }
        }
    }
}

class Main {
    public static void main(String[] args){
        try {
            StudentSystem studentSystem = new StudentSystem("src/main/java/Week11/students.csv");
            System.out.println("Number of students : " + studentSystem.noOfStudents());
            System.out.println("Student with id 1 : " + studentSystem.getStudentById(1));
            System.out.println("Student with highest GPA : " + studentSystem.getHighestGPAStudent());
            System.out.println("Student with longest name : " + studentSystem.getLongestNameStudent());
        } catch (IOException | InvalidStudentDataException e){
            System.out.println("Exception : " + e.getMessage());
        }
    }
}
