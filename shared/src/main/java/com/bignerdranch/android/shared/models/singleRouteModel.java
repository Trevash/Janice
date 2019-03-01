package com.bignerdranch.android.shared.models;

import com.bignerdranch.android.shared.exceptions.RouteAlreadyClaimedException;
import com.bignerdranch.android.shared.models.colors.playerColorsEnum;
import com.bignerdranch.android.shared.models.colors.routesColorsEnum;

public class singleRouteModel extends abstractRoute{
    private routesColorsEnum trainColor;
    private playerIDModel claimer = null;

    public singleRouteModel(cityModel city1, cityModel city2, int length, routesColorsEnum defaultColor){
        super(city1, city2, length);
        this.trainColor = defaultColor;
    }

    public playerIDModel getClaimer() {
        return claimer;
    }

    @Override
    public void claim(playerIDModel claimer, routesColorsEnum newColor) throws RouteAlreadyClaimedException {
        if(this.claimer != null){
            throw new RouteAlreadyClaimedException("Route from " + this.getCity1().getName() + " to " + this.getCity2().getName() + " already claimed!");
        }
        this.claimer = claimer;
        this.trainColor = newColor;
    }

    public routesColorsEnum getTrainColor() {
        return trainColor;
    }

    public void setTrainColor(routesColorsEnum newColor) {
        this.trainColor = newColor;
    }

    @Override
    public boolean claimable() {
        return this.claimer == null;
    }

    @Override
    public boolean isClaimedBy(playerIDModel pm) {
        if(claimer == null){
            return false;
        }
        else return pm.getValue().equals(claimer.getValue());
    }
}
