package com.bignerdranch.android.shared.models;

import com.bignerdranch.android.shared.Constants;
import com.bignerdranch.android.shared.exceptions.RouteAlreadyClaimedException;
import com.bignerdranch.android.shared.models.colors.routeColorEnum;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * doubleRouteModelFew extends abstractDoubleRote, and is used in games of 2 or 3 players
 */
public class doubleRouteModelFew extends abstractDoubleRoute {
    /**
     * Does not change anything from the abstractDoubleRoute constructor
     */
    public doubleRouteModelFew(cityModel city1, cityModel city2, int length,
                               routeColorEnum color1, routeColorEnum color2, routeIDModel routeID) {
        super(city1, city2, length, color1, color2, routeID);
    }

    @Override
    public void claim(playerIDModel claimer, routeColorEnum color) throws RouteAlreadyClaimedException {
        if (this.claimer1 != null)
            throw new RouteAlreadyClaimedException("Route from " + this.getCity1().getName() + " to " + this.getCity2().getName() + " already claimed!");
        this.claimer1 = claimer;
    }

    /**
     * createDoubleRoutesFew is called during the creation of the game model only in games with 4 or 5 players
     */
    public static List<abstractRoute> createDoubleRoutesFew() {
        List<abstractRoute> routes = new ArrayList<>();

        routes.add(new doubleRouteModelFew(Constants.Cities.VANCOUVER, Constants.Cities.SEATTLE, 1, routeColorEnum.GRAY, routeColorEnum.GRAY, new routeIDModel()));
        routes.add(new doubleRouteModelFew(Constants.Cities.PORTLAND, Constants.Cities.SEATTLE, 1, routeColorEnum.GRAY, routeColorEnum.GRAY, new routeIDModel()));
        routes.add(new doubleRouteModelFew(Constants.Cities.PORTLAND, Constants.Cities.SAN_FRANCISCO, 5, routeColorEnum.GREEN, routeColorEnum.PURPLE, new routeIDModel()));
        routes.add(new doubleRouteModelFew(Constants.Cities.SAN_FRANCISCO, Constants.Cities.LOS_ANGELES, 3, routeColorEnum.PURPLE, routeColorEnum.YELLOW, new routeIDModel()));
        routes.add(new doubleRouteModelFew(Constants.Cities.SAN_FRANCISCO, Constants.Cities.SALT_LAKE_CITY, 5, routeColorEnum.ORANGE, routeColorEnum.WHITE, new routeIDModel()));

        routes.add(new doubleRouteModelFew(Constants.Cities.SALT_LAKE_CITY, Constants.Cities.DENVER, 3, routeColorEnum.RED, routeColorEnum.YELLOW, new routeIDModel()));
        routes.add(new doubleRouteModelFew(Constants.Cities.DENVER, Constants.Cities.KANSAS_CITY, 4, routeColorEnum.ORANGE, routeColorEnum.BLACK, new routeIDModel()));
        routes.add(new doubleRouteModelFew(Constants.Cities.DULUTH, Constants.Cities.OMAHA, 2, routeColorEnum.GRAY, routeColorEnum.GRAY, new routeIDModel()));
        routes.add(new doubleRouteModelFew(Constants.Cities.OMAHA, Constants.Cities.KANSAS_CITY, 1, routeColorEnum.GRAY, routeColorEnum.GRAY, new routeIDModel()));
        routes.add(new doubleRouteModelFew(Constants.Cities.KANSAS_CITY, Constants.Cities.OKLAHOMA_CITY, 2, routeColorEnum.GRAY, routeColorEnum.GRAY, new routeIDModel()));

        routes.add(new doubleRouteModelFew(Constants.Cities.OKLAHOMA_CITY, Constants.Cities.DALLAS, 2, routeColorEnum.GRAY, routeColorEnum.GRAY, new routeIDModel()));
        routes.add(new doubleRouteModelFew(Constants.Cities.DALLAS, Constants.Cities.HOUSTON, 1, routeColorEnum.GRAY, routeColorEnum.GRAY, new routeIDModel()));
        routes.add(new doubleRouteModelFew(Constants.Cities.KANSAS_CITY, Constants.Cities.ST_LOUIS, 2, routeColorEnum.PURPLE, routeColorEnum.BLUE, new routeIDModel()));
        routes.add(new doubleRouteModelFew(Constants.Cities.ST_LOUIS, Constants.Cities.CHICAGO, 2, routeColorEnum.GREEN, routeColorEnum.WHITE, new routeIDModel()));
        routes.add(new doubleRouteModelFew(Constants.Cities.CHICAGO, Constants.Cities.PITTSBURGH, 3, routeColorEnum.ORANGE, routeColorEnum.BLACK, new routeIDModel()));

        routes.add(new doubleRouteModelFew(Constants.Cities.PITTSBURGH, Constants.Cities.NEW_YORK_CITY, 2, routeColorEnum.GREEN, routeColorEnum.WHITE, new routeIDModel()));
        routes.add(new doubleRouteModelFew(Constants.Cities.NEW_YORK_CITY, Constants.Cities.BOSTON, 2, routeColorEnum.YELLOW, routeColorEnum.RED, new routeIDModel()));
        routes.add(new doubleRouteModelFew(Constants.Cities.BOSTON, Constants.Cities.MONTREAL, 2, routeColorEnum.GRAY, routeColorEnum.GRAY, new routeIDModel()));
        routes.add(new doubleRouteModelFew(Constants.Cities.NEW_YORK_CITY, Constants.Cities.WASHINGTON, 2, routeColorEnum.ORANGE, routeColorEnum.BLACK, new routeIDModel()));
        routes.add(new doubleRouteModelFew(Constants.Cities.WASHINGTON, Constants.Cities.RALEIGH, 2, routeColorEnum.GRAY, routeColorEnum.GRAY, new routeIDModel()));

        routes.add(new doubleRouteModelFew(Constants.Cities.RALEIGH, Constants.Cities.ATLANTA, 2, routeColorEnum.GRAY, routeColorEnum.GRAY, new routeIDModel()));
        routes.add(new doubleRouteModelFew(Constants.Cities.NEW_ORLEANS, Constants.Cities.ATLANTA, 4, routeColorEnum.YELLOW, routeColorEnum.ORANGE, new routeIDModel()));

        return routes;
    }

    /**
     * Overrides the claim function of abstractRoute, essentially works the same as singleRoute, ignoring the inherited claimer2
     */
    @Override
    public void claim(playerIDModel claimer) throws RouteAlreadyClaimedException {
        if (this.claimer1 != null)
            throw new RouteAlreadyClaimedException("Route from " + this.getCity1().getName() + " to " + this.getCity2().getName() + " already claimed!");
        this.claimer1 = claimer;
    }

    /**
     * Claimable only checks claimer1, ignoring claimer2.
     */
    public boolean claimable() {
        return this.claimer1 == null;
    }
}
