package Week2;

import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class Task4 {
    public static void main(String[] args){
        Scanner reader = new Scanner(System.in);
        int first = parseInt(reader.nextLine());
        int last = parseInt(reader.nextLine());
        if(first > last){
            System.out.println(first + ", " + last);
        }else {
            while( first <= last){
                System.out.println(first);
                first++;
            }
        }
    }
}
