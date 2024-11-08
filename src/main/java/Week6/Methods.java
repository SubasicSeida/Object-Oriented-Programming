package Week6;

import java.util.Arrays;

public class Methods {
    public static void main(String[] args){
//        int[] values = {6, 5, 8, 6, 11};
//        System.out.println("smallest: " + Methods.smallest(values));

//        int[] values = {6, 5, 8, 6, 11};
//        System.out.println("Index of smallest: " + indexOfSmallest(values));

//        int[] values = {-1, 6, 9, 8, 12};
//        System.out.println(indexOfTheSmallestStartingFrom(values, 1));
//        System.out.println(indexOfTheSmallestStartingFrom(values, 2));
//        System.out.println(indexOfTheSmallestStartingFrom(values, 4));

//        int[] values = {3, 2, 5, 4, 8};
//        System.out.println(Arrays.toString(values));
//        swap(values, 1, 0);
//        System.out.println(Arrays.toString(values));
//        swap(values, 0, 3);
//        System.out.println(Arrays.toString(values));

        int[] array = {5, 1, 3, 4, 2};
        printElegantly(array);
    }

    public static int smallest(int[] array){
        int smallestNum = array[0];
        for(int number : array){
           if(smallestNum >= number){
               smallestNum = number;
           }
        }
        return smallestNum;
    }

    public static int indexOfSmallest(int[] array){
        int smallest = array[0];
        int index = 0;
        for(int i = 0; i < array.length; i++){
            if(smallest >= array[i]){
                smallest = array[i];
                index = i;
            }
        }
        return index;
    }

    public static int indexOfTheSmallestStartingFrom(int[] array, int start){
        int smallest = array[start];
        int index = start;
        for(int i = start; i < array.length; i++){
            if(smallest >= array[i]){
                smallest = array[i];
                index = i;
            }
        }
        return index;
    }

    public static void swap(int[] array, int index1, int index2){
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public static void printElegantly(int[] array){
        for(int i = 0; i < array.length; i++){
            System.out.print(array[i]);
            if (i != array.length - 1){
                System.out.print(", ");
            }
        }
    }
}
