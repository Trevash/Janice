package com.bignerdranch.android.shared.exceptions;

public class CannotDrawTrainCardException extends Exception{
    public CannotDrawTrainCardException() {
        super();
    }

    public CannotDrawTrainCardException(String s) {
        super(s);
    }

    public CannotDrawTrainCardException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public CannotDrawTrainCardException(Throwable throwable) {
        super(throwable);
    }
}
