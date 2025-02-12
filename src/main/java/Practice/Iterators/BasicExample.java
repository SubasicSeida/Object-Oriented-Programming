package Practice.Iterators;

import java.util.*;

class BasicExample {
    public static void main(String[] args){
        List<String> names = new ArrayList<>(Arrays.asList("Alice", "Bob", "Charlie"));

        Iterator<String> iterator = names.iterator();
        while(iterator.hasNext()){
            String name = iterator.next();
            if(name.startsWith("C")){
                iterator.remove();
            }
        }

        ListIterator<String> listIterator = names.listIterator();
        while(listIterator.hasNext()){
            System.out.println("Forward : " + listIterator.next());
        }
        while(listIterator.hasPrevious()){
            System.out.println("Backwards : " + listIterator.previous());
        }
    }
}

class CustomIteratorExample implements Iterator<Integer> {
    private int array[];
    private int index = 0;

    public CustomIteratorExample(int array[]){
        this.array = array;
    }

    @Override
    public boolean hasNext(){
        return index < array.length;
    }

    @Override
    public Integer next(){
        return array[index++];
    }

    public static void main(String[] args){
        int[] numbers = {10, 20, 30};

        CustomIteratorExample iterator = new CustomIteratorExample(numbers);
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
