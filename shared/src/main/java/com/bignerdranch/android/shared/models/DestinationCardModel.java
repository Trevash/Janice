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
    
    public cityModel getCity1(){
    	return city1;
    }
    public cityModel getCity2(){
    	return city2;
    }
    public int getPointValue() {
    	return pointValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DestinationCardModel that = (DestinationCardModel) o;

        if (getPointValue() != that.getPointValue()) return false;
        if (!getCity1().equals(that.getCity1())) return false;
        return getCity2().equals(that.getCity2());
    }

    @Override
    public int hashCode() {
        int result = getCity1().hashCode();
        result = 31 * result + getCity2().hashCode();
        result = 37 * result + getPointValue();
        return result;
    }
}
