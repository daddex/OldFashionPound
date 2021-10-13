package org.daddex.oldfashionpound.exception;

public class BadAdditionInputException extends RuntimeException{
    public BadAdditionInputException(String message, Object input) {
        super(String.format("Error %s for input %s ",message,input));
    }

    public BadAdditionInputException(String message, Throwable cause) {
        super(message, cause);
    }
}
