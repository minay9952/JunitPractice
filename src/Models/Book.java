package Models;

import java.util.Date;

public class Book {
    private String isbn;
    private String title;
    private String author;
    private int pages;
    private int copies;
    private Date borrowDate;
    private Date returnDate;

    public Book(String isbn, String title, String author, int pages, int copies){
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.copies = copies;
        this.borrowDate = null;
        this.returnDate = null;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPages() {
        return pages;
    }

    public int getCopies() {
        return copies;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }
}
