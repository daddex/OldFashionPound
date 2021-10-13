package org.daddex.oldfashionpound.exception;

public class AmountExceededException extends RuntimeException{
    public AmountExceededException(String message, Object input) {
        super(String.format("Error %s for input %s ",message,input));
    }

    public AmountExceededException(String message, Throwable cause) {
        super(message, cause);
    }
}
