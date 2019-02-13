package com.bignerdranch.android.shared.exceptions;

public class InvalidAuthorizationException extends Exception {
    public InvalidAuthorizationException() {
        super();
    }

    public InvalidAuthorizationException(String s) {
        super(s);
    }

    public InvalidAuthorizationException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public InvalidAuthorizationException(Throwable throwable) {
        super(throwable);
    }
}
