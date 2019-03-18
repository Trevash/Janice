package com.bignerdranch.android.shared.models;

import com.bignerdranch.android.shared.models.colors.routeColorEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * abstractDoubleRoute is an abstract interface that extends abstractRoute
 * It has two of its own children, doubleRouteFew and doubleRouteMany, with few being used in games with 2 or 3 players and many being use with 4 or 5 players
 */
abstract public class abstractDoubleRoute extends abstractRoute{
    /**
     * This interface adds two train colors and two claimers.
     * claimer2 is only used by doubleRouteMany
     */
    protected routeColorEnum trainColor1;
    protected routeColorEnum trainColor2;
    protected playerIDModel claimer1 = null;
    protected playerIDModel claimer2 = null;

    /**
     * Adds two routeColorEnums to the constructor
     */
    public abstractDoubleRoute(cityModel city1, cityModel city2, int length, routeColorEnum color1, routeColorEnum color2) {
        super(city1, city2, length);
        trainColor1 = color1;
        trainColor2 = color2;
    }

    /**
     * Getters for trainColors and claimers
     */
    public routeColorEnum getTrainColor1() {
        return trainColor1;
    }

    public routeColorEnum getTrainColor2() {
        return trainColor2;
    }

    public playerIDModel getClaimer1() {
        return claimer1;
    }

    public playerIDModel getClaimer2() {
        return claimer2;
    }

    /**
     * When updating the UI, each side of a double route is listed as available if it is not claimed.  These functions are used there.
     */
    public boolean claimableRoute1() {
        return this.claimer1 == null;
    }

    public boolean claimableRoute2() {
        return this.claimer2 == null;
    }
}
