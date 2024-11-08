package Week4.people;

public class Teacher extends Person{
    private int salary;

    public Teacher(String name, String address, int age, String country, int salary){
        super(name, address, age, country);
        this.salary = salary;
    }

    public String toString(){
        return this.getName() + "\n\t" + this.getAddress() + "\n\tsalary: " + this.salary + " euros/month";
    }
}
