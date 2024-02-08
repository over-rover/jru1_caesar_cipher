package exceptions;

public class FileException extends RuntimeException {
    public FileException(String message) {
        super(message);
    }

    public FileException(String message, Exception e) {
        super(message, e);
    }
}