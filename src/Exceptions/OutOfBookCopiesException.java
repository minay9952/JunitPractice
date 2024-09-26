package Exceptions;

public class OutOfBookCopiesException extends Exception {
    public OutOfBookCopiesException(String message) {
        super(message);
    }

    public OutOfBookCopiesException(String message, Throwable cause) {
        super(message, cause);
    }
}
