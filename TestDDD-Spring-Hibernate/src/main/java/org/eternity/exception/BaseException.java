package org.eternity.exception;

@SuppressWarnings("serial")
public class BaseException extends RuntimeException {
    private int status = -1;
    
    public static final int Order_Limit_Exceeded = 1;
    
    public BaseException(Throwable e){
        super(e);
    }
    
    public BaseException(int status){
        this.status = status;
    }
    
    public BaseException(int status, Throwable e) {
        super(e);
        this.status = status;
    }
    
    public int getStatus() {
        return status;
    }
}