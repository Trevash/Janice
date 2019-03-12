package com.bignerdranch.android.shared.models;

import java.util.UUID;

public class playerIDModel {
    private String value;

    playerIDModel() {
        value = UUID.randomUUID().toString();
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        playerIDModel that = (playerIDModel) o;

        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return getValue().hashCode();
    }
}
