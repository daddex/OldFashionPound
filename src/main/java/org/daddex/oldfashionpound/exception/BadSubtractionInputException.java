package org.daddex.oldfashionpound.exception;

public class BadSubtractionInputException extends RuntimeException{
    public BadSubtractionInputException(String message, Object input) {
        super(String.format("Error %s for input %s ",message,input));
    }

    public BadSubtractionInputException(String message, Throwable cause) {
        super(message, cause);
    }
}
