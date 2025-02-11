package Practice.FileManip;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

record Student(int id, String name, int age, String grade){};

class Files {
    public static void saveToFile(String filename, List<Student> students){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){
            writer.write("ID,Name,Age,Grade\n");
            for(Student student : students){
                writer.write(student.id() + "," + student.name() + "," + student.age() + "," + student.grade() + "\n");
            }
        } catch (IOException e){
            System.out.println("Error saving to file : " + e.getMessage());
        }
    }

    public static void appendToFile(String filename, Student student){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))){
            writer.write(student.id() + "," + student.name() + "," + student.age() + "," + student.grade() + "\n");
        } catch (IOException e){
            System.out.println("Error saving to file : " + e.getMessage());
        }
    }

    public static Optional<List<Student>> getStudentsFromFile(String filename){
        List<Student> students = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String line = reader.readLine();
            while((line = reader.readLine()) != null){
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                int age = Integer.parseInt(parts[2]);
                String grade = parts[3];
                students.add(new Student(id, name, age, grade));
            }
        } catch (IOException e){
            System.out.println("Error reading from file : " + e.getMessage());
        }
        if(students.isEmpty()) return Optional.empty();
        return Optional.of(students);
    }

    public static void main(String[] args){
        Student student1 = new Student(1, "John", 20, "A");
        Student student2 = new Student(2, "Emma", 21, "B");
        Student student3 = new Student(3, "Michael", 23, "C");
        Student student4 = new Student(4, "Jane", 22, "A");
        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);

        String filename = "students.csv";
        saveToFile(filename, students);
        appendToFile(filename, student4);
        System.out.println(getStudentsFromFile(filename));
    }
}