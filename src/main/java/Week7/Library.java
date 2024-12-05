package Week7;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class Book {
    private String title;
    private String author;
    private String ISBN;
    private String status;

    // Constructor
    public Book(String title, String author, String ISBN, String status) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Book{title='" + title + "', author='" + author + "', ISBN='" + ISBN + "', status='" + status + "'}";
    }
}

class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public Optional<Book> findBookByISBN(String ISBN) {
        return books.stream()
                .filter(book -> book.getISBN().equals(ISBN))
                .findFirst();
    }
}

class Main {
    public static void main(String[] args){
        Library library = new Library();

        library.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald", "12345", "Available"));
        library.addBook(new Book("1984", "George Orwell", "67890", "Checked Out"));

        String searchISBN1 = "12345";
        Optional<Book> book1 = library.findBookByISBN(searchISBN1);
        System.out.println("Found book: " + book1.orElse(new Book("Unknown", "Unknown", "00000", "N/A")));

        String searchISBN2 = "99999";
        Optional<Book> book2 = library.findBookByISBN(searchISBN2);
        System.out.println("Found book: " + book2.orElse(new Book("Unknown", "Unknown", "00000", "N/A")));
    }
}