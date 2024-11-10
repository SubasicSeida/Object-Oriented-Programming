package Week6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        int[] array = {-3, 2, 3, 4, 7, 8, 12};
//        Scanner reader = new Scanner(System.in);
//        System.out.println("Values of the array: " + Arrays.toString(array));
//        System.out.println();
//        System.out.println("Enter searched number: ");
//        String searchedValue = reader.nextLine();
//        boolean result = BinarySearch.search(array, Integer.parseInt(searchedValue));
//        System.out.println(result);

//        NightSky nightSky = new NightSky(8, 4);
//        nightSky.print();
//        System.out.println("Number of stars: " + nightSky.getStarsInLastPrint());

        Book cheese = new Book("Cheese Problems Solved", "Woodhead Publishing", 2007);
        System.out.println(cheese.title());
        System.out.println(cheese.publisher());
        System.out.println(cheese.publishingYear());
        System.out.println(cheese);

        Library Library = new Library();
        Library.addBook(cheese);
        Library.addBook(new Book("NHL Hockey", "Stanley Kupp", 1952));
        Library.addBook(new Book("Battle Axes", "Tom A. Hawk", 1851));
        Library.addBook(new Book("The Stinky Cheese Man and Other Fairly Stupid Tales", "Penguin Group", 1992));

        System.out.println("--------------------------");
        ArrayList<Book> result = Library.searchByTitle("Cheese");
        for (Book book : result) {
            System.out.println(book);
        }

        System.out.println("--------------------------");
        for (Book book : Library.searchByPublisher("Penguin Group  ")) {
            System.out.println(book);
        }

        System.out.println("--------------------------");
        for (Book book : Library.searchByYear(1851)) {
            System.out.println(book);
        }

        HashMap<String, String> names = new HashMap<>();
        names.put("matti", "mage");
        names.put("mikael", "mixu");
        names.put("arto", "arppa");
        System.out.println(names.get("mikael"));
    }
}
