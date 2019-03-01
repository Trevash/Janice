package com.bignerdranch.android.shared.models;

import com.bignerdranch.android.shared.exceptions.RouteAlreadyClaimedException;
import com.bignerdranch.android.shared.models.colors.routesColorsEnum;

public class doubleRouteModelMany extends abstractDoubleRoute{
    public doubleRouteModelMany(cityModel city1, cityModel city2, int length, routesColorsEnum.Color defaultColor) {
        super(city1, city2, length, defaultColor);
    }

    @Override
    public void claim(playerIDModel claimer, routesColorsEnum.Color newColor) throws RouteAlreadyClaimedException {
        if (this.claimer1 != null && this.claimer2 != null)
            throw new RouteAlreadyClaimedException("Route from " + this.getCity1().getName() + " to " + this.getCity2().getName() + " already claimed!");

        if(claimer1 == null) {
            this.claimer1 = claimer;
            this.trainColor1 = newColor;
        }
        else {
            this.claimer2 = claimer;
            this.trainColor2 = newColor;
        }
    }
}
