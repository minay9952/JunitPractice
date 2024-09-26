package Exceptions;

public class MemberOverdueBooksException extends Exception{
    public MemberOverdueBooksException(String message) {
        super(message);
    }

    public MemberOverdueBooksException(String message, Throwable cause) {
        super(message, cause);
    }
}
