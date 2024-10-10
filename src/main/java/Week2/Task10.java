package Week2;

import java.util.Scanner;
import java.util.Random;

public class Task10 {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        Random random = new Random();
        int num = random.nextInt(101);
        int counter = 0;
        int guess = -1;

        while (guess != num) {
            System.out.print("Guess the number : ");
            guess = reader.nextInt();
            counter++;

            if (guess < num) {
                System.out.println("Higher! -  This is your " + counter + ". guess!");
            } else if (guess > num) {
                System.out.println("Lower! -  This is your " + counter + ". guess!");
            } else {
                System.out.println("You are correct!");
                System.out.println("You made " + counter + " guesses.");

            }
        }
    }
}
