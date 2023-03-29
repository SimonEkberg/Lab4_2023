package service;

import java.sql.SQLException;

public class CleaningManagerServiceException extends SQLException {
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
