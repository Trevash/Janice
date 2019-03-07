package com.bignerdranch.android.shared.models;

import java.util.UUID;

public class routeIDModel {
    private String value;

    public routeIDModel() {
        value = UUID.randomUUID().toString();
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        routeIDModel that = (routeIDModel) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return getValue().hashCode();
                //Objects.hash(getValue());
    }
}