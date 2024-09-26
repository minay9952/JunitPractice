package Exceptions;

public class MaximumBorrowedBooksExceededException extends Exception{
    public MaximumBorrowedBooksExceededException(String message) {
        super(message);
    }

    public MaximumBorrowedBooksExceededException(String message, Throwable cause) {
        super(message, cause);
    }
}
