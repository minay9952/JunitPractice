package Exceptions;

public class MemberExistException extends Exception{

    public MemberExistException(String message){
        super(message);
    }

    public MemberExistException(String message, Throwable cause){
        super(message, cause);
    }
}
