package Week4.shapes;

public class Rectangle extends Shape{
    private double width;
    private double height;

    public Rectangle(String color, FillType filled, double width, double height){
        super(color, filled);
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String displayInfo(){
        return "Color : " + this.getColor() + "\nFillType : " + this.getFilled() + "\nWidth : " + this.width +
                "\nHeight : " + this.height;
    }

    public String getArea(){
        return "Area : " + (this.width * this.height);
    }
}
