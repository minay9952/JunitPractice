package Exceptions;

public class BookExistException extends Exception{

    public BookExistException(String message) {
        super(message);
    }

    public BookExistException(String message, Throwable cause) {
        super(message, cause);
    }

}
