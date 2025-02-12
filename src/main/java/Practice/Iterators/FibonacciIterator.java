package Practice.Iterators;

import java.util.Iterator;

class FibonacciIterator implements Iterator<Integer> {
    private final int limit;
    private int count = 0;
    private int previous = 0;
    private int current = 1;

    public FibonacciIterator(int limit){
        this.limit = limit;
    }

    public boolean hasNext(){
        return count < limit;
    }

    public Integer next(){
        if(!hasNext()){
            throw new IllegalStateException("No more elements");
        }
        int result;
        if(count == 0) result = 0;
        else if(count == 1) result = 1;
        else {
            result = previous + current;
            previous = current;
            current = result;
        }
        count++;
        return result;
    }
}

class FibonacciDemo {
    public static void main(String[] args){
        FibonacciIterator iterator = new FibonacciIterator(10);
        while(iterator.hasNext()){
            System.out.println(iterator.next() + " ");
        }
    }
}

