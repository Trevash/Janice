package com.bignerdranch.android.shared.models;

import com.bignerdranch.android.shared.exceptions.RouteAlreadyClaimedException;
import com.bignerdranch.android.shared.models.colors.routeColorEnum;

public class doubleRouteModelFew extends abstractDoubleRoute {
    public doubleRouteModelFew(cityModel city1, cityModel city2, int length, routeColorEnum color) {
        super(city1, city2, length, color);
    }

    @Override
    public void claim(playerIDModel claimer) throws RouteAlreadyClaimedException {
        if (this.claimer1 == null)
            throw new RouteAlreadyClaimedException("Route from " + this.getCity1().getName() + " to " + this.getCity2().getName() + " already claimed!");
        this.claimer1 = claimer;
    }

    public boolean claimable() {
        return (this.claimer1 == null) && (this.claimer2 == null);
    }
}
