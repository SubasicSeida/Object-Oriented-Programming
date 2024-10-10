package Week2;

import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class Task8 {

    public static void drawInvertedPyramid(int num) {
        for (int i = num; i >= 1; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
    public static void main(String[] args){
        Scanner reader = new Scanner(System.in);
        int num = parseInt(reader.nextLine());
        drawInvertedPyramid(num);
    }
}
