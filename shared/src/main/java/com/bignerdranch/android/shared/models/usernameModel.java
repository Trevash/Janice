package com.bignerdranch.android.shared.models;

import com.bignerdranch.android.shared.exceptions.DuplicateException;

public class usernameModel {
    private String value;

    public usernameModel(String newValue) throws DuplicateException {
        if(newValue == null || newValue.length() == 0) {
            throw new IllegalArgumentException("No username received!");
        }
        else if(!newValue.matches("[a-zA-Z0-9]+")) {
            throw new IllegalArgumentException("Username used spaces or illegal characters!");
        }

        this.value = newValue;
    }

    public String getValue() {
        return value;
    }
}
