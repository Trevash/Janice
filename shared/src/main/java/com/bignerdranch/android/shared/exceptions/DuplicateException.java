package com.bignerdranch.android.shared.exceptions;

public class DuplicateException extends Exception {
    public DuplicateException(){}
    public DuplicateException(String message){
        super(message);
    }
    public DuplicateException(Throwable cause){
        super(cause);
    }
    public DuplicateException(String message, Throwable cause) {
        super(message, cause);
    }
}