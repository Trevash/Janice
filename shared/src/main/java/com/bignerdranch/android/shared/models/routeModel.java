package com.bignerdranch.android.shared.models;

import com.bignerdranch.android.shared.models.colors.routesColorsEnum;

public class routeModel {
    private cityModel cityA;
    private cityModel cityB;
    private routesColorsEnum color;
    private usernameModel claimer;

    public routeModel(cityModel cityA, cityModel cityB, routesColorsEnum defaultColor){
        this.cityA = cityA;
        this.cityB = cityB;
        this.color = defaultColor;
    }

    public usernameModel getClaimer() {
        return claimer;
    }

    public void setClaimer(usernameModel claimer) {
        this.claimer = claimer;
    }

    public routesColorsEnum getColor() {
        return color;
    }

    public void setColor(routesColorsEnum color) {
        this.color = color;
    }

    public cityModel getCityA() {
        return cityA;
    }

    public cityModel getCityB() {
        return cityB;
    }
}
