package Week6;

import java.util.Arrays;

public class BinarySearch {
    public static boolean search(int[] array, int searchFor){
        Arrays.sort(array);
        int low = 0;
        int high = array.length - 1;
        int mid = (low + high)/2;
        while(low <= high){
            if (array[mid] == searchFor){
                return true;
            }else if (array[mid] > searchFor){
                high = mid - 1;
                mid = (low + high)/2;
            }else {
                low = mid + 1;
                mid = (low + high)/2;
            }
        }
        return false;
    }
}
