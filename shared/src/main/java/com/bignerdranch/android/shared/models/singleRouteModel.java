package com.bignerdranch.android.shared.models;

import com.bignerdranch.android.shared.exceptions.RouteAlreadyClaimedException;
import com.bignerdranch.android.shared.models.colors.routeColorEnum;

public class singleRouteModel extends abstractRoute{
    private routeColorEnum trainColor;
    private playerIDModel claimer = null;

    public singleRouteModel(cityModel city1, cityModel city2, int length, routeColorEnum color){
        super(city1, city2, length);
        this.trainColor = color;
    }

    public playerIDModel getClaimer() {
        return claimer;
    }

    @Override
    public void claim(playerIDModel claimer) throws RouteAlreadyClaimedException {
        if(this.claimer != null){
            throw new RouteAlreadyClaimedException("Route from " + this.getCity1().getName() + " to " + this.getCity2().getName() + " already claimed!");
        }
        this.claimer = claimer;
    }

    public routeColorEnum getTrainColor() {
        return trainColor;
    }

    public void setTrainColor(routeColorEnum newColor) {
        this.trainColor = newColor;
    }

    public boolean claimable() {
        return this.claimer == null;
    }

    public boolean isClaimedBy(playerIDModel pm) {
        if(claimer == null){
            return false;
        }
        else return pm.getValue().equals(claimer.getValue());
    }
}
