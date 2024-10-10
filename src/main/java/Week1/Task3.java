package Week1;

import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class Task3 {
    public static void main(String[] args){
        Scanner reader = new Scanner(System.in);
        int num1 = parseInt(reader.nextLine());
        int num2 = parseInt(reader.nextLine());
        int sum = num1 + num2;
        System.out.print(sum);
    }
}
