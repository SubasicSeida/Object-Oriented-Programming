package Week1;

import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class Task4 {
    public static void main(String[] args){
        Scanner reader = new Scanner(System.in);
        int num = parseInt(reader.nextLine());
        if (num > 0) {
            System.out.println("The number is positive.");
        } else {
            System.out.println("The number is not positive.");
        }
    }
}
