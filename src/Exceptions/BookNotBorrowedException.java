package Exceptions;

public class BookNotBorrowedException extends Exception{
    public BookNotBorrowedException(String message) {
        super(message);
    }

    public BookNotBorrowedException(String message, Throwable cause) {
        super(message, cause);
    }
}
