package com.bignerdranch.android.shared.models;

import com.bignerdranch.android.shared.exceptions.RouteAlreadyClaimedException;
import com.bignerdranch.android.shared.models.colors.routesColorsEnum;

abstract public class abstractRoute {
    private routeIDModel routeID;
    private cityModel city1;
    private cityModel city2;
    private int length;

    public abstractRoute(cityModel city1, cityModel city2, int length){
        this.city1 = city1;
        this.city2 = city2;
        this.length = length;
        this.routeID = new routeIDModel();
    }

    public routeIDModel getRouteID() {
        return routeID;
    }

    public cityModel getCity1() {
        return city1;
    }

    public void setCity2(cityModel city2) {
        this.city2 = city2;
    }

    public int getLength() {
        return length;
    }

    public cityModel getCity2() {
        return city2;
    }

    public boolean claimable(){return false;}

    public boolean isClaimedBy(playerIDModel pm){return false;}

    public void claim(playerIDModel claimer, routesColorsEnum.Color newColor) throws RouteAlreadyClaimedException{}
}


