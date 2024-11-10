package Week6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Generics {

    public static <T extends Number> String sumOfNumbers(List<T> numbers){
        double sumEven = 0;
        double sumOdd = 0;
        for(Number num : numbers){
            if(num.doubleValue() % 2 == 0){
                sumEven += num.doubleValue();
            }else {
                sumOdd += num.doubleValue();
            }
        }
        return "Sum of odd = " + sumOdd + ", sum of even = " + sumEven;
    }

    public static <T> List<T> reverseList(List<T> list){
        List<T> newList = new ArrayList<>(list);
        Collections.reverse(newList);
        return newList;
    }

    public static void main(String[] args){
        List<Number> numbers = new ArrayList<>();
        numbers.add(12);
        numbers.add(3.14);
        numbers.add(3L);
        System.out.println(sumOfNumbers(numbers));

        List<String> names = new ArrayList<>();
        names.add("Alice");
        names.add("James");
        System.out.println(reverseList(names));
    }
}
