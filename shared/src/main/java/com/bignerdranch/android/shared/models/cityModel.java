package com.bignerdranch.android.shared.models;

public class cityModel {
    private String name;
    private int xCoordinate; // off by ratio of a little under 2
    private int yCoordinate; // off by ration of about 1.7

    public cityModel(String name, int x, int y){
        this.name = name;
        xCoordinate = x;
        yCoordinate = y;
    }

    public String getName() {
        return name;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
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
