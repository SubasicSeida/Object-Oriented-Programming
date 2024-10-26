package Week4.shapes;

public class Circle extends Shape{
    private double radius;

    public static final double PI = 3.14159;

    public Circle(String color, FillType filled, double radius){
        super(color, filled);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public String displayInfo(){
        return "Color : " + this.getColor() + "\nFillType : " + this.getFilled() + "\nRadius : " + this.radius;
    }

    public String getArea(){
        return "Area : " + (this.radius * this.radius * PI);
    }

    public String calculateCircumference(double pi, double radius){
        this.radius = radius;
        return "Circumference : " + (this.radius * pi * 2);
    }

    public String calculateCircumference(double radius){
        this.radius = radius;
        return "Circumference : " + (this.radius * PI * 2);
    }
}
