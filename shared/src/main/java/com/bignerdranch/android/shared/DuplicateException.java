package com.bignerdranch.android.shared;

public class DuplicateException extends Exception { // TODO what would be a good name for this?
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