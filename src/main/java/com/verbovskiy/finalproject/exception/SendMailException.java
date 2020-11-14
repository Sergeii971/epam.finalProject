package com.verbovskiy.finalproject.exception;

/**
 * The type Send email exception.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public class SendMailException extends Exception {
    /**
     * Instantiates a new Send email exception.
     */
    public SendMailException() {
        super();
    }

    /**
     * Instantiates a new Send email exception.
     *
     * @param message the message
     */
    public SendMailException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Send email exception.
     *
     * @param message the message
     * @param throwable the throwable
     */
    public SendMailException(String message, Throwable throwable) {
        super(message, throwable);
    }

    /**
     * Instantiates a new Send email exception.
     *
     * @param throwable the throwable
     */
    public SendMailException(Throwable throwable) {
        super(throwable);
    }

}
