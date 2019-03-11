package com.bignerdranch.android.shared.models;

import com.bignerdranch.android.shared.exceptions.RouteAlreadyClaimedException;
import com.bignerdranch.android.shared.models.colors.routeColorEnum;

import java.util.ArrayList;
import java.util.List;

import static com.bignerdranch.android.shared.Constants.Cities.*;

public class singleRouteModel extends abstractRoute {
    private routeColorEnum trainColor;
    private playerIDModel claimer = null;

    public singleRouteModel(cityModel city1, cityModel city2, int length, routeColorEnum color) {
        super(city1, city2, length);
        this.trainColor = color;
    }

    public playerIDModel getClaimer() {
        return claimer;
    }

    @Override
    public void claim(playerIDModel claimer) throws RouteAlreadyClaimedException {
        if (this.claimer != null) {
            throw new RouteAlreadyClaimedException("Route from " + this.getCity1().getName() + " to " + this.getCity2().getName() + " already claimed!");
        }
        this.claimer = claimer;
    }

    public String toString(){
        return getCity1().getName() + " - " + getCity2().getName();
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
        if (claimer == null) {
            return false;
        } else return pm.getValue().equals(claimer.getValue());
    }

    public static List<abstractRoute> createSingleRoutes() {
        List<abstractRoute> routes = new ArrayList<>();
        routes.add(new singleRouteModel(SALT_LAKE_CITY, LAS_VEGAS, 4, routeColorEnum.GRAY));
        routes.add(new singleRouteModel(SALT_LAKE_CITY, HELENA, 3, routeColorEnum.PURPLE));
        routes.add(new singleRouteModel(VANCOUVER, CALGARY, 3, routeColorEnum.GRAY));
        routes.add(new singleRouteModel(CALGARY, SEATTLE, 4, routeColorEnum.GRAY));
        routes.add(new singleRouteModel(CALGARY, WINNIPEG, 6, routeColorEnum.WHITE));
        routes.add(new singleRouteModel(CALGARY, HELENA, 4, routeColorEnum.GRAY));
        routes.add(new singleRouteModel(HELENA, SEATTLE, 6, routeColorEnum.YELLOW));
        routes.add(new singleRouteModel(HELENA, WINNIPEG, 4, routeColorEnum.BLUE));
        routes.add(new singleRouteModel(HELENA, DULUTH, 6, routeColorEnum.ORANGE));
        routes.add(new singleRouteModel(HELENA, DENVER, 4, routeColorEnum.GREEN));
        routes.add(new singleRouteModel(HELENA, OMAHA, 5, routeColorEnum.RED));
        routes.add(new singleRouteModel(PORTLAND, SALT_LAKE_CITY, 6, routeColorEnum.BLUE));
        routes.add(new singleRouteModel(LAS_VEGAS, LOS_ANGELES, 2, routeColorEnum.GRAY));
        routes.add(new singleRouteModel(LOS_ANGELES, PHOENIX, 3, routeColorEnum.GRAY));
        routes.add(new singleRouteModel(LOS_ANGELES, EL_PASO, 6, routeColorEnum.BLACK));
        routes.add(new singleRouteModel(EL_PASO, PHOENIX, 3, routeColorEnum.GRAY));
        routes.add(new singleRouteModel(EL_PASO, OKLAHOMA_CITY, 5, routeColorEnum.YELLOW));
        routes.add(new singleRouteModel(EL_PASO, DALLAS, 4, routeColorEnum.RED));
        routes.add(new singleRouteModel(EL_PASO, HOUSTON, 6, routeColorEnum.GREEN));
        routes.add(new singleRouteModel(EL_PASO, SANTA_FE, 2, routeColorEnum.GRAY));
        routes.add(new singleRouteModel(DENVER, PHOENIX, 5, routeColorEnum.WHITE));
        routes.add(new singleRouteModel(PHOENIX, SANTA_FE, 3, routeColorEnum.GRAY));
        routes.add(new singleRouteModel(DENVER, SANTA_FE, 2, routeColorEnum.GRAY));
        routes.add(new singleRouteModel(DENVER, OKLAHOMA_CITY, 4, routeColorEnum.RED));
        routes.add(new singleRouteModel(DENVER, OMAHA, 4, routeColorEnum.PURPLE));
        routes.add(new singleRouteModel(OMAHA, CHICAGO, 4, routeColorEnum.BLUE));
        routes.add(new singleRouteModel(WINNIPEG, DULUTH, 4, routeColorEnum.BLACK));
        routes.add(new singleRouteModel(WINNIPEG, ST_MARIE, 6, routeColorEnum.GRAY));
        routes.add(new singleRouteModel(ST_MARIE, DULUTH, 3, routeColorEnum.GRAY));
        routes.add(new singleRouteModel(ST_MARIE, TORONTO, 2, routeColorEnum.GRAY));
        routes.add(new singleRouteModel(ST_MARIE, MONTREAL, 5, routeColorEnum.BLACK));
        routes.add(new singleRouteModel(MONTREAL, TORONTO, 3, routeColorEnum.GRAY));
        routes.add(new singleRouteModel(MONTREAL, NEW_YORK_CITY, 3, routeColorEnum.BLUE));
        routes.add(new singleRouteModel(TORONTO, DULUTH, 6, routeColorEnum.PURPLE));
        routes.add(new singleRouteModel(TORONTO, CHICAGO, 4, routeColorEnum.WHITE));
        routes.add(new singleRouteModel(TORONTO, PITTSBURGH, 2, routeColorEnum.GRAY));
        routes.add(new singleRouteModel(PITTSBURGH, WASHINGTON, 2, routeColorEnum.GRAY));
        routes.add(new singleRouteModel(PITTSBURGH, RALEIGH, 2, routeColorEnum.GRAY));
        routes.add(new singleRouteModel(PITTSBURGH, NASHVILLE, 4, routeColorEnum.YELLOW));
        routes.add(new singleRouteModel(ST_LOUIS, PITTSBURGH, 5, routeColorEnum.GREEN));
        routes.add(new singleRouteModel(ST_LOUIS, NASHVILLE, 2, routeColorEnum.GRAY));
        routes.add(new singleRouteModel(ST_LOUIS, LITTLE_ROCK, 2, routeColorEnum.GRAY));
        routes.add(new singleRouteModel(LITTLE_ROCK, NASHVILLE, 3, routeColorEnum.WHITE));
        routes.add(new singleRouteModel(LITTLE_ROCK, OKLAHOMA_CITY, 2, routeColorEnum.GRAY));
        routes.add(new singleRouteModel(LITTLE_ROCK, DALLAS, 2, routeColorEnum.GRAY));
        routes.add(new singleRouteModel(LITTLE_ROCK, NEW_ORLEANS, 3, routeColorEnum.GREEN));
        routes.add(new singleRouteModel(OKLAHOMA_CITY, SANTA_FE, 3, routeColorEnum.BLUE));
        routes.add(new singleRouteModel(NASHVILLE, RALEIGH, 3, routeColorEnum.BLACK));
        routes.add(new singleRouteModel(NASHVILLE, ATLANTA, 1, routeColorEnum.GRAY));
        routes.add(new singleRouteModel(ATLANTA, CHARLESTON, 2, routeColorEnum.GRAY));
        routes.add(new singleRouteModel(CHARLESTON, RALEIGH, 2, routeColorEnum.GRAY));
        routes.add(new singleRouteModel(MIAMI, CHARLESTON, 4, routeColorEnum.PURPLE));
        routes.add(new singleRouteModel(MIAMI, ATLANTA, 5, routeColorEnum.BLUE));
        routes.add(new singleRouteModel(MIAMI, NEW_ORLEANS, 6, routeColorEnum.RED));
        routes.add(new singleRouteModel(HOUSTON, NEW_ORLEANS, 2, routeColorEnum.GRAY));
        return routes;
    }
}
