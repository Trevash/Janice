package com.bignerdranch.android.shared.models;

import java.util.Objects;
import java.util.UUID;

public class gameIDModel {
    private String value;

    public gameIDModel() {
        value = UUID.randomUUID().toString();
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        gameIDModel that = (gameIDModel) o;
        return this.getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return getValue().hashCode();
    }
}
