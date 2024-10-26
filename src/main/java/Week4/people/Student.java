package Week4.people;

import java.util.ArrayList;

public class Student extends Person{
    private int credits;
    private int studentId;
    private ArrayList<Integer> grades;

    public Student(String name, String address, int age, String country, int studentId, ArrayList<Integer> grades){
        super(name, address, age, country);
        this.credits = 0;
        this.studentId = studentId;
        this.grades = new ArrayList<>();
    }

    public void addGrade(int grade){
        grades.add(grade);
    }

    public ArrayList<Integer> getGrades(){
        return this.grades;
    }

    public void setGrades(int grade){
        this.grades.add(grade);
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void study(){
        this.credits++;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getCredits(){
        return this.credits;
    }

    public String toString(){
        return this.getName() + "\n\t" + this.getAddress() + "\n\tcredits: " + this.credits;
    }
}
