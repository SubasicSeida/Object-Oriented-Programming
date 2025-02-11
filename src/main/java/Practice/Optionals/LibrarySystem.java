package Practice.Optionals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

record Book(int id, String title, String author, boolean isBorrowed){}

class Library {
    List<Book> books = new ArrayList<>();

    public Library(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks(){
        return books;
    }

    public void addBook(Book book){
        books.add(book);
    }

    public Optional<Book> searchByTitle(String title){
        for(Book book : books){
            if(book.title().contains(title)){
                return Optional.of(book);
            }
        }
        return Optional.empty();
    }

    public Optional<List<Book>> searchByAuthor(String author){
        List<Book> booksOfAuthor = new ArrayList<Book>();
        for(Book book : books){
            if(book.author() == author) booksOfAuthor.add(book);
        }
        return booksOfAuthor.isEmpty() ? Optional.empty() : Optional.of(booksOfAuthor);
    }
}