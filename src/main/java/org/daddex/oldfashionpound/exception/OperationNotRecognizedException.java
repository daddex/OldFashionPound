package org.daddex.oldfashionpound.exception;

public class OperationNotRecognizedException extends RuntimeException{
    public OperationNotRecognizedException(String message, Object input) {
        super(String.format("Error %s for input %s ",message,input));
    }

    public OperationNotRecognizedException(String message, Throwable cause) {
        super(message, cause);
    }
}
