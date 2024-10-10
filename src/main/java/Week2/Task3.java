package Week2;

import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class Task3 {
    public static void main(String[] args){
        int input = -1, sum = 0;
        Scanner reader = new Scanner(System.in);
        while(input != 0){
            System.out.println("Enter a number : ");
            input = parseInt(reader.nextLine());
            sum += input;
            System.out.println("Sum : " + sum);
        }
        System.out.println("Final sum is : " + sum);
    }
}
