package Week4.shapes;

public class Main {
    public static void main(String[] args){
        Shape shape = new Shape("green", FillType.FILLED);
        Circle circle = new Circle("yellow", FillType.NOT_FILLED, 5.0);
        Rectangle rectangle = new Rectangle("red", FillType.FILLED, 4.3, 2.1);

        System.out.println(shape.displayInfo());
        System.out.println(circle.displayInfo());
        System.out.println(rectangle.displayInfo());
    }
}
