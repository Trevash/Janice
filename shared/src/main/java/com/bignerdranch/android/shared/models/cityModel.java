package com.bignerdranch.android.shared.models;

public class cityModel {
    private String name;

    public cityModel(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        cityModel cityModel = (cityModel) o;

        return getName().equals(cityModel.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

}
