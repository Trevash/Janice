package com.bignerdranch.android.shared.models;

public class passwordModel {
    private String value;

    public passwordModel(String newValue) {
        if(newValue == null || newValue.length() == 0) {
            throw new IllegalArgumentException("No password received!");
        }
        else if(!newValue.matches("[a-zA-Z0-9]+")){
            throw new IllegalArgumentException("Password used spaces or illegal characters!");
        }

        this.value = newValue;
    }

    public String getValue() {
        return value;
    }
}
