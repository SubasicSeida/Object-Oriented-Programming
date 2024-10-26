package Week4.shapes;

public class Shape {
    private String color;
    private FillType filled;

    public Shape(String color, FillType filled){
        this.color = color;
        this.filled = filled;
    }

    public FillType getFilled() {
        return filled;
    }

    public void setFilled(FillType filled) {
        this.filled = filled;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor(){
        return this.color;
    }

    public String displayInfo(){
        return "Color : " + this.color + "\nFillType : " + this.filled;
    }
}
