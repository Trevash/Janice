package com.bignerdranch.android.shared.models;

import com.bignerdranch.android.shared.models.colors.playerColorsEnum;
import com.bignerdranch.android.shared.models.colors.routesColorsEnum;

public class routeModel {
    private cityModel cityA;
    private cityModel cityB;
    private routesColorsEnum.Color color;
    private usernameModel claimer;

    public routeModel(cityModel cityA, cityModel cityB, routesColorsEnum.Color defaultColor){
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

    public routesColorsEnum.Color getColor() {
        return color;
    }

    public void setColor(routesColorsEnum.Color color) {
        this.color = color;
    }

    public cityModel getCityA() {
        return cityA;
    }

    public cityModel getCityB() {
        return cityB;
    }
}
