package com.bignerdranch.android.shared.models;

import com.bignerdranch.android.shared.models.colors.routeColorEnum;

import java.util.ArrayList;
import java.util.List;

abstract public class abstractDoubleRoute extends abstractRoute{
    protected routeColorEnum trainColor;
    protected playerIDModel claimer1 = null;
    protected playerIDModel claimer2 = null;

    public abstractDoubleRoute(cityModel city1, cityModel city2, int length, routeColorEnum color) {
        super(city1, city2, length);
        trainColor = color;
    }


    public playerIDModel getClaimer1() {
        return claimer1;
    }

    public routeColorEnum getTrainColor() {
        return trainColor;
    }

    public void setTrainColor(routeColorEnum trainColor) {
        this.trainColor = trainColor;
    }

    public playerIDModel getClaimer2() {
        return claimer2;
    }

    public boolean claimableRoute1() {
        return this.claimer1 == null;
    }

    public boolean claimableRoute2() {
        return this.claimer2 == null;
    }
}
