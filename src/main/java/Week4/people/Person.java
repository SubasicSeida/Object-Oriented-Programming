package Week4.people;

public class Person {
    private String name;
    private String address;
    private int age;
    private String country;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Person(String name, String address, int age, String country){
        this.name = name;
        this.address = address;
        this.age = age;
        this.country = country;
    }

    public String getName(){
        return this.name;
    }
    public String getAddress(){
        return this.address;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String toString(){
        return this.name + "\n\t" + this.address;
    }
}



