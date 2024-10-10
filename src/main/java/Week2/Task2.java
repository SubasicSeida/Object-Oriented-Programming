package Week2;

import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class Task2 {
    public static void main(String[] args){
        Scanner reader = new Scanner(System.in);
        int num1, num2, num3;
        System.out.println("Enter three numbers : ");
        num1 = parseInt(reader.nextLine());
        num2 = parseInt(reader.nextLine());
        num3 = parseInt(reader.nextLine());
        System.out.println("Sum : " + (num1 + num2 + num3));

    }
}
