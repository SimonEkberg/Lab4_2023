package service;

public class IdServiceEception extends RuntimeException {
    public IdServiceEception(){}
    public IdServiceEception(String message){
        super(message);
    }
    public IdServiceEception(String message, Throwable cause){
        super(message, cause);
    }
    public IdServiceEception(Throwable cause){
        super(cause);
    }
}
