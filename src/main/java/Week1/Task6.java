package Week1;

import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class Task6 {
    public static void main(String[] args){
        Scanner reader = new Scanner(System.in);
        int num1 = parseInt(reader.nextLine());
        int num2 = parseInt(reader.nextLine());
        if (num1 > num2) {
            System.out.println("The greater number is: " + num1);
        } else if (num2 > num1) {
            System.out.println("The greater number is: " + num2);
        } else {
            System.out.println("Both numbers are equal: " + num1);
        }
    }
}
