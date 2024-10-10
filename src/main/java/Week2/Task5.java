package Week2;

import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class Task5 {
    public static void main(String[] args){
        Scanner reader = new Scanner(System.in);
        int n = parseInt(reader.nextLine());
        int counter = 0, sum = 0;
        while(counter <= n){
            sum += (int)Math.pow(2, counter);
            counter++;
        }
        System.out.println(sum);
    }
}
