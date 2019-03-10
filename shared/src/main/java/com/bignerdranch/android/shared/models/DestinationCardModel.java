package com.bignerdranch.android.shared.models;

public class DestinationCardModel {
    private cityModel city1;
    private cityModel city2;
    private int pointValue;
    private boolean isComplete = false;

    public DestinationCardModel(cityModel city1, cityModel city2, int pointValue){
        this.city1 = city1;
        this.city2 = city2;
        this.pointValue = pointValue;
    }

    public String getFormattedDestinationCard() {
        return city1.getName() + " - " + city2.getName() + ": " + Integer.toString(pointValue);
    }

    @Override
    public String toString() {
        return city1.getName() + " - " + city2.getName() + ": " + Integer.toString(pointValue);
    }
}
