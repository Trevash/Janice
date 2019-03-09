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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        usernameModel that = (usernameModel) o;

        return value != null ? value.equals(that.value) : that.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    public String getValue() {
        return value;
    }
}
