package com.bignerdranch.android.shared.exceptions;

public class RouteAlreadyClaimedException extends Exception {
    public RouteAlreadyClaimedException() {
        super();
    }

    public RouteAlreadyClaimedException(String s) {
        super(s);
    }

    public RouteAlreadyClaimedException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public RouteAlreadyClaimedException(Throwable throwable) {
        super(throwable);
    }
}
