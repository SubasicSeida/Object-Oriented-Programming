package Week4.people;

public class Teacher extends Person{
    private int salary;

    public Teacher(String name, String address, int salary){
        super(name, address);
        this.salary = salary;
    }

    public String toString(){
        return this.getName() + "\n\t" + this.getAddress() + "\n\tsalary: " + this.salary + " euros/month";
    }
}
