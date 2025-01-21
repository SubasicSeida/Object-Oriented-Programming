package Practice.DatabaseConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

enum Status {
    AVAILABLE,
    ISSUED,
    RESERVED
}

class Book {
    private int id;
    private String title;
    private String author;
    private Status status;

    public Book(int id, String title, String author, Status status) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", status=" + status +
                '}';
    }
}

class Library {
    List<Book> books = new ArrayList<>();

    public Library(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooksByAuthor(String author) {
        return books.stream()
                .filter(book -> book.getAuthor().equals(author))
                .collect(Collectors.toList());
    }

    public List<Book> getAvailableBooks() {
        return books.stream()
                .filter(book -> book.getStatus().equals(Status.AVAILABLE))
                .collect(Collectors.toList());
    }

    public List<Book> getBooksWithKeyword(String keyword) {
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void printBookTitles() {
        books.forEach(book -> System.out.println(book.getTitle().toUpperCase()));
    }
}

class DbConnect2 {
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/library";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "hFW5yLylRpx!=2)";

    private Connection connection = null;

    public DbConnect2() {
        try {
            connection = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection(){
        return connection;
    }

    public void createBook(Book book) {
        String query = "INSERT INTO library (id, title, author, status) VALUES (?, ?, ?, ?)";

        try(PreparedStatement pstmt = connection.prepareStatement(query)){
            pstmt.setInt(1, book.getId());
            pstmt.setString(2, book.getTitle());
            pstmt.setString(3, book.getAuthor());
            pstmt.setString(4, book.getStatus().name());

            pstmt.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Book> getBooksFromDb() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM library";

        try(Statement stmt = connection.createStatement()){
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()){
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                Status status = Status.valueOf(rs.getString("status"));

                books.add(new Book(id, title, author, status));
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }

        return books;
    }

    public void updateBookStatus(int id, Status status) {
        String query = "UPDATE library SET status = ? WHERE id = ?";

        try(PreparedStatement pstmt = connection.prepareStatement(query)){
            pstmt.setString(1, status.name());
            pstmt.setInt(2, id);

            pstmt.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void deleteBookFromDb(int id) {
        String query = "DELETE FROM library WHERE id = ?";

        try(PreparedStatement pstmt = connection.prepareStatement(query)){
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
