package org.daddex.oldfashionpound.exception;

public class BadDivisionInputException extends RuntimeException{
    public BadDivisionInputException(String message, Object input) {
        super(String.format("Error %s for input %s ",message,input));
    }

    public BadDivisionInputException(String message, Throwable cause) {
        super(message, cause);
    }
}
