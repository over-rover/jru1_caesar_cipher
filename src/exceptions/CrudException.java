package exceptions;

public class CrudException extends RuntimeException {

    public CrudException(String message, Exception e) {
        super(message, e);
    }
}