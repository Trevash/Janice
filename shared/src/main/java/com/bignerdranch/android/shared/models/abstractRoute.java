package com.bignerdranch.android.shared.models;

import com.bignerdranch.android.shared.Constants;
import com.bignerdranch.android.shared.exceptions.RouteAlreadyClaimedException;

/**
 * abstractRoute is the abstract interface that all other route classes we use inherit from.
 * Each child class will implement its color and claimer tracking in separate ways
 */

abstract public class abstractRoute {
    /**
     * It includes all member variables that all three types of our implemented routes share in common, including getters.
     * - These include routeID, the length of the route, and the two cities the route connects.
     */
    private routeIDModel routeID;
    private cityModel city1;
    private cityModel city2;
    private int length;

    public abstractRoute(cityModel city1, cityModel city2, int length) {
        this.city1 = city1;
        this.city2 = city2;
        this.length = length;
        this.routeID = new routeIDModel();
    }

    /**
     * Getters include: getRouteID, getCity1, getCity2, getLength
     */
    public routeIDModel getRouteID() {
        return routeID;
    }
    public cityModel getCity1() {
        return city1;
    }
    public int getLength() {
        return length;
    }
    public cityModel getCity2() {
        return city2;
    }

    /**
     * The 'claim' function is different for each type of route, and is here to be overridden later.
     */
    public void claim(playerIDModel claimer) throws RouteAlreadyClaimedException{}

}