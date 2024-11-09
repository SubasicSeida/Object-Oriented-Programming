package Week6;

public class Book {
    private String title;
    private String publisher;
    private int publishingYear;

    public Book(String title, String publisher, int publishingYear){
        this.title = title;
        this.publisher = publisher;
        this.publishingYear = publishingYear;
    }

    public String title(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String publisher(){
        return publisher;
    }

    public void setPublisher(String publisher){
        this.publisher = publisher;
    }

    public int publishingYear(){
        return publishingYear;
    }

    public void setPublishingYear(int publishingYear){
        this.publishingYear = publishingYear;
    }

    @Override
    public String toString(){
        return title + " " +  publisher + " " + publishingYear;
    }
}
