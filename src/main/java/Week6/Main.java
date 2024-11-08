package Week6;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
//        int[] array = {-3, 2, 3, 4, 7, 8, 12};
//        Scanner reader = new Scanner(System.in);
//        System.out.println("Values of the array: " + Arrays.toString(array));
//        System.out.println();
//        System.out.println("Enter searched number: ");
//        String searchedValue = reader.nextLine();
//        boolean result = BinarySearch.search(array, Integer.parseInt(searchedValue));
//        System.out.println(result);

        NightSky nightSky = new NightSky(8, 4);
        nightSky.print();
        System.out.println("Number of stars: " + nightSky.getStarsInLastPrint());
    }
}
