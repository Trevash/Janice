package com.bignerdranch.android.shared.models;

import com.bignerdranch.android.shared.Constants;
import com.bignerdranch.android.shared.exceptions.RouteAlreadyClaimedException;
import com.bignerdranch.android.shared.models.colors.routeColorEnum;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * doubleRouteModelMany extends abstractDoubleRoute, and is used in games of 2 or 3 players
 */
public class doubleRouteModelMany extends abstractDoubleRoute{
    /**
     * No changes to the inherited constructor
     */
    public doubleRouteModelMany(cityModel city1, cityModel city2, int length,
                                routeColorEnum color1, routeColorEnum color2) {
        super(city1, city2, length, color1, color2);
    }

    /**
     * createDoubleRoutesMany is called during the creation of the game model only in games with 4 or 5 players
     */
    public static List<abstractRoute> createDoubleRoutesMany() {
        List<abstractRoute> routes = new ArrayList<>();
        routes.add(new doubleRouteModelMany(Constants.Cities.VANCOUVER, Constants.Cities.SEATTLE, 1, routeColorEnum.GRAY, routeColorEnum.GRAY));
        routes.add(new doubleRouteModelMany(Constants.Cities.PORTLAND, Constants.Cities.SEATTLE, 1, routeColorEnum.GRAY, routeColorEnum.GRAY));
        routes.add(new doubleRouteModelMany(Constants.Cities.PORTLAND, Constants.Cities.SAN_FRANCISCO, 5, routeColorEnum.GREEN, routeColorEnum.PURPLE));
        routes.add(new doubleRouteModelMany(Constants.Cities.SAN_FRANCISCO, Constants.Cities.LOS_ANGELES, 3, routeColorEnum.PURPLE, routeColorEnum.YELLOW));
        routes.add(new doubleRouteModelMany(Constants.Cities.SAN_FRANCISCO, Constants.Cities.SALT_LAKE_CITY, 5, routeColorEnum.ORANGE, routeColorEnum.WHITE));

        routes.add(new doubleRouteModelMany(Constants.Cities.SALT_LAKE_CITY, Constants.Cities.DENVER, 3, routeColorEnum.RED, routeColorEnum.YELLOW));
        routes.add(new doubleRouteModelMany(Constants.Cities.DENVER, Constants.Cities.KANSAS_CITY, 4, routeColorEnum.ORANGE, routeColorEnum.BLACK));
        routes.add(new doubleRouteModelMany(Constants.Cities.DULUTH, Constants.Cities.OMAHA, 2, routeColorEnum.GRAY, routeColorEnum.GRAY));
        routes.add(new doubleRouteModelMany(Constants.Cities.OMAHA, Constants.Cities.KANSAS_CITY, 1, routeColorEnum.GRAY, routeColorEnum.GRAY));
        routes.add(new doubleRouteModelMany(Constants.Cities.KANSAS_CITY, Constants.Cities.OKLAHOMA_CITY, 2, routeColorEnum.GRAY, routeColorEnum.GRAY));

        routes.add(new doubleRouteModelMany(Constants.Cities.OKLAHOMA_CITY, Constants.Cities.DALLAS, 2, routeColorEnum.GRAY, routeColorEnum.GRAY));
        routes.add(new doubleRouteModelMany(Constants.Cities.DALLAS, Constants.Cities.HOUSTON, 1, routeColorEnum.GRAY, routeColorEnum.GRAY));
        routes.add(new doubleRouteModelMany(Constants.Cities.KANSAS_CITY, Constants.Cities.ST_LOUIS, 2, routeColorEnum.PURPLE, routeColorEnum.BLUE));
        routes.add(new doubleRouteModelMany(Constants.Cities.ST_LOUIS, Constants.Cities.CHICAGO, 2, routeColorEnum.GREEN, routeColorEnum.WHITE));
        routes.add(new doubleRouteModelMany(Constants.Cities.CHICAGO, Constants.Cities.PITTSBURGH, 3, routeColorEnum.ORANGE, routeColorEnum.BLACK));

        routes.add(new doubleRouteModelMany(Constants.Cities.PITTSBURGH, Constants.Cities.NEW_YORK_CITY, 2, routeColorEnum.GREEN, routeColorEnum.WHITE));
        routes.add(new doubleRouteModelMany(Constants.Cities.NEW_YORK_CITY, Constants.Cities.BOSTON, 2, routeColorEnum.YELLOW, routeColorEnum.RED));
        routes.add(new doubleRouteModelMany(Constants.Cities.BOSTON, Constants.Cities.MONTREAL, 2, routeColorEnum.GRAY, routeColorEnum.GRAY));
        routes.add(new doubleRouteModelMany(Constants.Cities.NEW_YORK_CITY, Constants.Cities.WASHINGTON, 2, routeColorEnum.ORANGE, routeColorEnum.BLACK));
        routes.add(new doubleRouteModelMany(Constants.Cities.WASHINGTON, Constants.Cities.RALEIGH, 2, routeColorEnum.GRAY, routeColorEnum.GRAY));

        routes.add(new doubleRouteModelMany(Constants.Cities.RALEIGH, Constants.Cities.ATLANTA, 2, routeColorEnum.GRAY, routeColorEnum.GRAY));
        routes.add(new doubleRouteModelMany(Constants.Cities.NEW_ORLEANS, Constants.Cities.ATLANTA, 4, routeColorEnum.YELLOW, routeColorEnum.ORANGE));

        return routes;
    }

    /**
     * Overrides the claim function of abstractRoute
     * This is the only time that claimer2 is used, as only when using this instance of abstractRoute can this route be claimed twice
     */
    @Override
    public void claim(playerIDModel claimer) throws RouteAlreadyClaimedException {
        if (this.claimer1 != null && this.claimer2 != null)
            throw new RouteAlreadyClaimedException("Route from " + this.getCity1().getName() + " to " + this.getCity2().getName() + " already claimed!");

        if(claimer1 == null) {
            this.claimer1 = claimer;
        }
        else {
            this.claimer2 = claimer;
        }
    }
}
