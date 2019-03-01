package com.bignerdranch.android.shared.models;

import com.bignerdranch.android.shared.models.colors.routesColorsEnum;

import java.util.ArrayList;
import java.util.List;

abstract public class abstractDoubleRoute extends abstractRoute{
    protected routesColorsEnum.Color trainColor1;
    protected playerIDModel claimer1 = null;

    protected routesColorsEnum.Color trainColor2;
    protected playerIDModel claimer2 = null;

    public abstractDoubleRoute(cityModel city1, cityModel city2, int length, routesColorsEnum.Color defaultColor) {
        super(city1, city2, length);
        trainColor1 = defaultColor;
        trainColor2 = defaultColor;
    }

    public routesColorsEnum.Color getTrainColor1() {
        return trainColor1;
    }

    public void setTrainColor1(routesColorsEnum.Color trainColor1) {
        this.trainColor1 = trainColor1;
    }

    public playerIDModel getClaimer1() {
        return claimer1;
    }

    public routesColorsEnum.Color getTrainColor2() {
        return trainColor2;
    }

    public void setTrainColor2(routesColorsEnum.Color trainColor2) {
        this.trainColor2 = trainColor2;
    }

    public playerIDModel getClaimer2() {
        return claimer2;
    }
}
