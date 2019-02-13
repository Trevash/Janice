package com.bignerdranch.android.shared.exceptions;

public class GameNotFoundException extends Exception {
    public GameNotFoundException() {
        super();
    }

    public GameNotFoundException(String s) {
        super(s);
    }

    public GameNotFoundException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public GameNotFoundException(Throwable throwable) {
        super(throwable);
    }
}
