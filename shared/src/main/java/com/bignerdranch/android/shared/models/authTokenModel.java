package com.bignerdranch.android.shared.models;

import java.util.Objects;
import java.util.UUID;

public class authTokenModel {
    private String value;

    public authTokenModel() {
        value = UUID.randomUUID().toString();
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        authTokenModel that = (authTokenModel) o;
        return this.getValue().equals(that.getValue());
                //Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return getValue().hashCode();
    }
}
