package Exceptions;

public class ExpiredDateException extends Exception{
    public ExpiredDateException(String message) {
        super(message);
    }

    public ExpiredDateException(String message, Throwable cause) {
        super(message, cause);
    }
}
