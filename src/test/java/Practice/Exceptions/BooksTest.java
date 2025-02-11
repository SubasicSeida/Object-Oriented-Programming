package Practice.Exceptions;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class BooksTest {
    private Library library = new Library("testBooks.txt");

    @Test
    void testGetBooksById_Valid() throws BookNotFoundException {
        Optional<Book> book = library.getBookById("1");
        assertTrue(book.isPresent());
        assertEquals("Test Book", book.get().getTitle());
    }

    @Test
    void getBookById_Invalid() throws BookNotFoundException {
        assertThrows(BookNotFoundException.class, () -> library.getBookById("999"));
    }

    @Test
    void testBorrowBook_Valid() throws BookUnavailableException, BookNotFoundException {
        library.borrowBook("1");
        Optional<Book> book = library.getBookById("1");
        assertTrue(book.isPresent());
        assertFalse(book.get().isAvailable());
    }

    @Test
    void testBorrowBook_Invalid() throws BookUnavailableException {
        library.borrowBook("1");
        assertThrows(BookUnavailableException.class, () -> library.borrowBook("1"));
    }

    @Test
    void testGetAvailableBooks(){
        List<Book> availableBooks = library.getAvailableBooks();
        assertFalse(availableBooks.isEmpty());
    }

    @Test
    void testValidateBookData(){
        Book book = new Book(null, "title", "author");
        assertThrows(InvalidBookDataException.class, () -> library.validateBookInfo(book));
    }
}
