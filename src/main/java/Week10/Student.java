package Week10;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

class Student {
    private String name;
    private int id;
    private List<Double> grades;

    public Student(String name, int id){
        this.name = name;
        this.id = id;
        grades = new ArrayList<>();
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public void addGrade(double grade){
        grades.add(grade);
    }

    public List<Double> getGrades(){
        return grades;
    }

    public String printInfo(){
        return "Name : " + name + ", ID : " + id + ", Grades : " + grades;
    }
}

class GradeAnalyzer{
    private List<Double> grades;
    public GradeAnalyzer(List<Double> grades){
        this.grades = grades;
    }

    public double calculateAverage(){
        if(grades.size() > 0){
            double sum = 0;
            for(Double grade : grades){
                sum += grade;
            }
            return sum/grades.size();
        } else {
            return 0;
        }
    }

    public String printSummary(){
        return "Grades : " + grades + ", Average : " + calculateAverage();
    }
}

class Start {
    public static void main(String[] args){
        Student student1 = new Student("Student 1", 111);
        student1.addGrade(78.3);
        student1.addGrade(93.6);
        GradeAnalyzer analyzer = new GradeAnalyzer(student1.getGrades());

//        printFields(student1);
//        printFields(analyzer);
        printMethods(student1);
        printMethods(analyzer);

    }

    public static void printFields(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        for(Field field : fields){
            field.setAccessible(true);
            try {
                System.out.println(field.getName() + " : " + field.get(obj));
            } catch (IllegalAccessException e){
                e.printStackTrace();
            }
        }
    }

    public static void printMethods(Object obj){
        Method[] methods = obj.getClass().getDeclaredMethods();
        for(Method method : methods){
            if(method.getName().startsWith("calculate") || method.getName().startsWith("print")){
                try {
                    method.setAccessible(true);
                    if(method.getReturnType() != void.class){
                        Object result = method.invoke(obj);
                        System.out.println(method.getName() + " result : " + result);
                    } else {
                        System.out.println(method.getName() + " was invoked.");
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}