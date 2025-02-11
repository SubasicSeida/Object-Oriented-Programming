package Practice.Exceptions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class Book {
    private String id;
    private String title;
    private String author;
    private boolean isAvailable = true;

    public Book(String id, String title, String author){
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                '}';
    }
}

class BookNotFoundException extends Exception {
    public BookNotFoundException(String message){
        super(message);
    }
}

class EmptyLibraryException extends RuntimeException {
    public EmptyLibraryException(String message){
        super(message);
    }
}

class BookUnavailableException extends Exception {
    public BookUnavailableException(String message){
        super(message);
    }
}

class InvalidBookDataException extends Exception {
    public InvalidBookDataException(String message){
        super(message);
    }
}

class Library {
    List<Book> books = new ArrayList<>();

    public Library(String filename){
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String line = "";
            while((line = reader.readLine()) != null){
                String[] parts = line.split(",");
                String id = parts[0].trim();
                String title = parts[1].trim();
                String author = parts[2].trim();
                Book book = new Book(id, title, author);
                if(validateBookInfo(book)) books.add(book);
            }
            System.out.println("Library initialized with " + books.size() + " books.");
        } catch(IOException e){
            System.out.println("Error : " + e.getMessage());
        }
    }

    public boolean validateBookInfo(Book book) {
        try {
            if (book == null || book.getId() == null || book.getTitle() == null || book.getAuthor() == null) {
                throw new InvalidBookDataException("Book data is not valid: missing required fields.");
            }
            return true;
        } catch (InvalidBookDataException e) {
            System.out.println("Exception Caught: " + e.getMessage());
            return false;
        }
    }

    public Optional<Book> getBookById(String id) throws BookNotFoundException {
        for(Book book : books){
            if(book.getId().equals(id)) return Optional.of(book);
        }
        throw new BookNotFoundException("Book not found");
    }

    public void borrowBook(String id) throws BookUnavailableException {
        for(Book book : books){
            if(book.getId().equals(id)){
                if(book.isAvailable()){
                    book.setAvailable(false);
                } else throw new BookUnavailableException("Book is already borrowed.");
            }
        }
    }

    public List<Book> getAvailableBooks() throws EmptyLibraryException {
        List<Book> available = new ArrayList<>();
        if(books.size() > 0){
            for(Book book : books){
                if(book.isAvailable()) available.add(book);
            }
            return available;
        } else throw new EmptyLibraryException("Library has no books.");
    }
}
