package Week6;

import java.util.ArrayList;

public class Library {
    private ArrayList<Book> books;

    public Library(){
        books = new ArrayList<>();
    }

    public void addBook(Book newBook){
        books.add(newBook);
    }

    public void printBooks(){
        for(Book book : books){
            System.out.println(book);
        }
    }

    public ArrayList<Book> searchByTitle(String title){
        ArrayList<Book> found = new ArrayList<>();
        for(Book book : books){
            if(StringUtils.included(book.title(), title)){
                found.add(book);
            }
        }
        return found;
    }

    public ArrayList<Book> searchByPublisher(String publisher){
        ArrayList<Book> found = new ArrayList<>();
        for(Book book : books){
            if(StringUtils.included(book.publisher(), publisher) ){
                found.add(book);
            }
        }
        return found;
    }

    public ArrayList<Book> searchByYear(int year){
        ArrayList<Book> found = new ArrayList<>();
        for(Book book : books){
            if(book.publishingYear() == year){
                found.add(book);
            }
        }
        return found;
    }
}
