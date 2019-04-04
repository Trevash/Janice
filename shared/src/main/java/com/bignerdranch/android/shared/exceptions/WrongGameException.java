package com.bignerdranch.android.shared.exceptions;

public class WrongGameException extends RuntimeException {
    public WrongGameException() {
        super();
    }

    public WrongGameException(String s) {
        super(s);
    }

    public WrongGameException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public WrongGameException(Throwable throwable) {
        super(throwable);
    }
}
