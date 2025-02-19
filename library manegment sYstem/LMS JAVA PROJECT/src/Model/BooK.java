package Model;

public class BooK {

    private String bookName;
    private String author;
    private String publishYear;

    public BooK(String bookName, String author, String publishYear) {
        this.bookName = bookName;
        this.author = author;
        this.publishYear = publishYear;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublishYear() {
        return publishYear;
    }
}
