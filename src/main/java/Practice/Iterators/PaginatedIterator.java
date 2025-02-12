package Practice.Iterators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

class PaginatedIterator<T> implements Iterator<T> {
    private List<T> data;
    private int pageSize;
    private int currentIndex = 0;
    private int currentPage = 0;

    public PaginatedIterator(List<T> data, int pageSize){
        this.data = data;
        this.pageSize = pageSize;
    }

    @Override
    public boolean hasNext(){
        return currentIndex < data.size();
    }

    @Override
    public T next(){
        if(!hasNext()) throw new NoSuchElementException();
        return data.get(currentIndex++);
    }

    public boolean hasNextPage(){
        return currentIndex < data.size();
    }

    public List<T> nextPage(){
        if(!hasNext()) throw new NoSuchElementException("No more pages");
        List<T> pageElements = new ArrayList<>();
        int endIndex = Math.min(currentIndex + pageSize, data.size());
        while(currentIndex < endIndex){
            pageElements.add(data.get(currentIndex));
            currentIndex++;
        }
        currentPage++;
        currentIndex = endIndex;
        return pageElements;
    }

    public void restartPagination(){
        currentIndex = 0;
        currentPage = 0;
    }

    public int getCurrentPage() {
        return currentPage + 1;
    }
}

class Demo {
    public static void main(String[] args){
        List<String> data = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            data.add("Item " + i);
        }

        PaginatedIterator<String> iterator = new PaginatedIterator<>(data, 5);

        System.out.println("Testing next() method:");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println();
        iterator.restartPagination();

        System.out.println("Testing nextPage() method:");
        while (iterator.hasNextPage()) {
            System.out.println("Page " + iterator.getCurrentPage() + ":");
            List<String> page = iterator.nextPage();
            for (String item : page) {
                System.out.println(item);
            }
            System.out.println();
        }
    }
}