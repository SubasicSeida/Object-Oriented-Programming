package Week2;

import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class Task6 {

    public static void printText(int num){
        String text = "In the beginning there were the swamp, the hoe and Java.";
        int counter = 0;
        while(counter < num){
            System.out.println(text);
            counter++;
        }
    }

    public static void main(String[] args){
        Scanner reader = new Scanner(System.in);
        int num = parseInt(reader.nextLine());
        printText(num);
    }
}
