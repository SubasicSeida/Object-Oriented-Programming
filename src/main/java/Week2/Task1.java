package Week2;

import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class Task1 {
    public static void main(String[] args){
        Scanner reader = new Scanner(System.in);
        int password = 123456;
        while(true){
            System.out.println("Guess the password : ");
            int guess = parseInt(reader.nextLine());
            if( guess == password ){
                System.out.println("This is the secret message.");
                break;
            }
        }
    }
}
