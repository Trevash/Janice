package com.bignerdranch.android.shared.exceptions;

public class RouteNotFoundException extends Exception {
    public RouteNotFoundException() {
        super();
    }

    public RouteNotFoundException(String s) {
        super(s);
    }

    public RouteNotFoundException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public RouteNotFoundException(Throwable throwable) {
        super(throwable);
    }
}