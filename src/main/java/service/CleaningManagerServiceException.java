package service;

public class CleaningManagerServiceException extends RuntimeException {
    public CleaningManagerServiceException(){}
    public CleaningManagerServiceException(String message){
        super(message);
    }
    public CleaningManagerServiceException(String message, Throwable cause){
        super(message, cause);
    }
    public CleaningManagerServiceException(Throwable cause){
        super(cause);
    }
}
