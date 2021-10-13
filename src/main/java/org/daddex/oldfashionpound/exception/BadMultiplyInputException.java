package org.daddex.oldfashionpound.exception;

public class BadMultiplyInputException extends RuntimeException{
    public BadMultiplyInputException(String message, Object input) {
        super(String.format("Error %s for input %s ",message,input));
    }

    public BadMultiplyInputException(String message, Throwable cause) {
        super(message, cause);
    }
}
