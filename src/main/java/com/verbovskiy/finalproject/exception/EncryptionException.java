package com.verbovskiy.finalproject.exception;

public class EncryptionException extends Exception{
    public EncryptionException() {
        super();
    }

    public EncryptionException(String message) {
        super(message);
    }

    public EncryptionException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public EncryptionException(Throwable throwable) {
        super(throwable);
    }
}
