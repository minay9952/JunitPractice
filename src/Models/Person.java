package Models;

import Exceptions.BookNotFoundException;
import Exceptions.MaximumBorrowedBooksExceededException;
import Exceptions.MemberOverdueBooksException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Person {
    private String name;
    private int age;
    private ArrayList<Book> borrowedBooks;

    public Person(String name, int age){
        this.name = name;
        this.age = age;
        this.borrowedBooks = new ArrayList();
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    /*
    * returns the number of books borrowed by this person
    * @return number of books
    * */
    public int booksCount(){
        return this.borrowedBooks.size();
    }

    /*
    * Adds a book to the member's borrowed books list
    * @param book the book to be added to the borrowed books list
    * @return confirmation message in case book borrowed successfully
    * @throws MaximumBorrowedBooksExceededException if the member has 5 or more books borrowed
    * @throws MemberOverdueBooksException if the member has one or more overdue books
    **/
    public String borrowBook(Book book) throws Exception {
        // check if the member has borrowed the maximum number of books
        if(this.borrowedBooks.size() >= 5)
            // has exceeded limit
            throw new MaximumBorrowedBooksExceededException("This member can't borrow more books");
        else{
            // check if the member has any overdue books
            for(int i=0; i < this.borrowedBooks.size(); i++){
                if(this.borrowedBooks.get(i).getReturnDate().isAfter(LocalDate.now()))
                    // has overdue books
                    throw new MemberOverdueBooksException("This member has overdue books");
            }
            // no overdue books. add to member's borrowed books
            Book newBook = new Book(book.getIsbn(), book.getTitle(), book.getAuthor(),book.getPages(),1);
            LocalDate today = LocalDate.now();
            newBook.setBorrowDate(today);
            newBook.setReturnDate(today.plusWeeks(1));
            this.borrowedBooks.add(newBook);
            return "Book Borrowed Successfully";
        }
    }

    /*
    * Adds a book to the member's borrowed books list with a specific return date
    * @param book the book to be added to the borrowed books list
    * @param date the return date of the book to be borrowed
    * @return confirmation message in case book borrowed successfully
    * @throws MaximumBorrowedBooksExceededException if the member has 5 or more books borrowed
    * @throws MemberOverdueBooksException if the member has one or more overdue books
    * */
    public String borrowBook(Book book, LocalDate date) throws Exception {
        // check if the member has borrowed the maximum number of books
        if(this.borrowedBooks.size() >= 5)
            // has exceeded limit
            throw new MaximumBorrowedBooksExceededException("This member can't borrow more books");
        else{
            // check if the member has any overdue books
            for(int i=0; i < this.borrowedBooks.size(); i++){
                if(this.borrowedBooks.get(i).getReturnDate().isAfter(LocalDate.now()))
                    // has overdue books
                    throw new MemberOverdueBooksException("This member has overdue books");
            }
            // no overdue books. add to member's borrowed books
            Book newBook = new Book(book.getIsbn(), book.getTitle(), book.getAuthor(),book.getPages(),1);
            newBook.setBorrowDate(LocalDate.now());
            newBook.setReturnDate(date);
            this.borrowedBooks.add(newBook);
            return "Book Borrowed Successfully";
        }
    }

    /*
    * returns a book borrowed by this member
    * @param isbn the isbn book the book to be returned
    * @returns a confirmation message in case book returned successfully
    * @throws BookNotFoundException if the book is not borrowed by this member
    * */
    public String returnBook(String isbn) throws BookNotFoundException {
        // search for the book to be returned
        for(int i=0; i < this.borrowedBooks.size(); i++){
            if(this.borrowedBooks.get(i).getIsbn().equals(isbn)){
                // book found
                this.borrowedBooks.remove(i);
                return "Book Returned Successfully";
            }
        }
        throw new BookNotFoundException("This book is not borrowed by the provided member");
    }

    /*
    * returns all the books borrowed by the member
    * */
    public void returnAllBooks(){
        this.borrowedBooks = new ArrayList<>();
    }

    /*
    * renews all overdue books borrowed by the member for an extra week
    * */
    public void renewBooks() {
        // loop on all the books
        for(int i=0; i < this.borrowedBooks.size(); i++){
            if(this.borrowedBooks.get(i).getReturnDate().isAfter(LocalDate.now())){
                // book is overdue
                this.borrowedBooks.get(i).setReturnDate(LocalDate.now().plusWeeks(1));
            }
        }
    }
}
