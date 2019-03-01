package com.bignerdranch.android.shared.models;

import com.bignerdranch.android.shared.models.colors.routeColorEnum;

public class routeModel {
    private cityModel cityA;
    private cityModel cityB;
    private routeColorEnum color;
    private usernameModel claimer;

    public routeModel(cityModel cityA, cityModel cityB, routeColorEnum defaultColor){
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

    public routeColorEnum getColor() {
        return color;
    }

    public void setColor(routeColorEnum color) {
        this.color = color;
    }

    public cityModel getCityA() {
        return cityA;
    }

    public cityModel getCityB() {
        return cityB;
    }
}
