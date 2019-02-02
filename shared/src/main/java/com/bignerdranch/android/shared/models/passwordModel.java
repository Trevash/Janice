package com.bignerdranch.android.shared.models;

public class passwordModel {
    private String value = null;

    public passwordModel(String newValue) throws Exception {
        if(newValue == null || newValue.length() == 0) {
            throw new Exception("No password recieved!");
        }
        else if(!newValue.matches("[a-zA-Z0-9]+")){
            throw new Exception("Password used spaces or illegal characters!");
        }

        this.value = newValue;
    }

    public String getValue() {
        return value;
    }
}
