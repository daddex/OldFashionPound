package org.daddex.oldfashionpound.exception;

public class BadInputException extends RuntimeException{
    public BadInputException(String message,Object input) {
        super(String.format("Error %s for input %s ",message,input));
    }

    public BadInputException(String message, Throwable cause) {
        super(message, cause);
    }
}
