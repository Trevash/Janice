package com.bignerdranch.android.shared;

import com.bignerdranch.android.shared.models.DestinationCardModel;
import com.bignerdranch.android.shared.models.cityModel;

/**
 * A class of constants that are intended for use throughout the project, including in the server
 */
public class Constants {
    public static final String IP_ADDRESS =
            "192.168.1.100"; // Jason's home IP address
            //"10.24.199.58";
            //"10.24.219.177";
            //"10.37.93.67"; // Regular IP address for group testing
            //"10.34.241.184"; // another IP address for group testing
    public static final int PORT = 8087;

    public static class Cities {
        // TODO have someone check over this list for completion - also confirm the name of St Marie
        public static final cityModel ATLANTA = new cityModel("Atlanta");
        public static final cityModel BOSTON = new cityModel("Boston");
        public static final cityModel CALGARY = new cityModel("Calgary");
        public static final cityModel CHICAGO = new cityModel("Chicago");
        public static final cityModel DALLAS = new cityModel("Dallas");

        public static final cityModel DENVER = new cityModel("Denver");
        public static final cityModel DULUTH = new cityModel("Duluth");
        public static final cityModel EL_PASO = new cityModel("El Paso");
        public static final cityModel HELENA = new cityModel("Helena");
        public static final cityModel HOUSTON = new cityModel("Houston");

        public static final cityModel KANSAS_CITY = new cityModel("Kansas City");
        public static final cityModel LAS_VEGAS = new cityModel("Las Vegas");
        public static final cityModel LITTLE_ROCK = new cityModel("Little Rock");
        public static final cityModel LOS_ANGELES = new cityModel("Los Angeles");
        public static final cityModel MIAMI = new cityModel("Miami");

        public static final cityModel MONTREAL = new cityModel("Montreal");
        public static final cityModel NASHVILLE = new cityModel("Nashville");
        public static final cityModel NEW_ORLEANS = new cityModel("New Orleans");
        public static final cityModel NEW_YORK_CITY = new cityModel("New York City");
        public static final cityModel OKLAHOMA_CITY = new cityModel("Oklahoma City");

        public static final cityModel PHOENIX  = new cityModel("Phoenix");
        public static final cityModel PITTSBURGH = new cityModel("Pittsburgh");
        public static final cityModel PORTLAND = new cityModel("Portland");
        public static final cityModel SALT_LAKE_CITY = new cityModel("Salt Lake City");
        public static final cityModel SANTA_FE = new cityModel("Santa Fe");

        public static final cityModel SAN_FRANCISCO = new cityModel("San Francisco");
        public static final cityModel ST_MARIE = new cityModel("St. Marie");
        public static final cityModel SEATTLE = new cityModel("Seattle");
        public static final cityModel TORONTO = new cityModel("Toronto");
        public static final cityModel VANCOUVER = new cityModel("Vancouver");
        public static final cityModel WINNIPEG = new cityModel("Winnipeg");

        //San Francisco to Atlanta (17), Portland to Nashville (17)
        //Vancouver to Montréal (20), Los Angeles to Miami (20)
        //Los Angeles to New York City (21)
        //Seattle to New York (22)
    }

    public static class DestinationCards {


        public static final DestinationCardModel DENVER_EL_PASO = new DestinationCardModel(Cities.DENVER, Cities.EL_PASO, 4);

        public static final DestinationCardModel HOUSTON_KANSAS_CITY = new DestinationCardModel(Cities.HOUSTON, Cities.KANSAS_CITY, 5);

        public static final DestinationCardModel ATLANTA_NEW_YORK = new DestinationCardModel(Cities.ATLANTA, Cities.NEW_YORK_CITY, 6);

        public static final DestinationCardModel CHICAGO_NEW_ORLEANS = new DestinationCardModel(Cities.CHICAGO, Cities.NEW_ORLEANS, 7);
        public static final DestinationCardModel CALGARY_SALT_LAKE = new DestinationCardModel(Cities.CALGARY, Cities.SALT_LAKE_CITY, 7);

        public static final DestinationCardModel HELENA_LOS_ANGELES = new DestinationCardModel(Cities.HELENA, Cities.LOS_ANGELES, 8);
        public static final DestinationCardModel DULUTH_HOUSTON = new DestinationCardModel(Cities.DULUTH, Cities.HOUSTON, 8);
        public static final DestinationCardModel NASHVILLE_ST_MARIE = new DestinationCardModel(Cities.NASHVILLE, Cities.ST_MARIE, 8);



        public static final DestinationCardModel ATLANTA_MONTREAL = new DestinationCardModel(Cities.ATLANTA, Cities.MONTREAL, 9);
        public static final DestinationCardModel OKLAHOMA_CITY_ST_MARIE = new DestinationCardModel(Cities.OKLAHOMA_CITY, Cities.ST_MARIE, 9);
        public static final DestinationCardModel LOS_ANGELES_SEATTLE = new DestinationCardModel(Cities.LOS_ANGELES, Cities.SEATTLE, 9);
        public static final DestinationCardModel CHICAGO_SANTA_FE = new DestinationCardModel(Cities.CHICAGO, Cities.SANTA_FE, 9);

        public static final DestinationCardModel DULUTH_EL_PASO = new DestinationCardModel(Cities.DULUTH, Cities.EL_PASO, 10);
        public static final DestinationCardModel MIAMI_TORONTO = new DestinationCardModel(Cities.MIAMI, Cities.TORONTO, 10);

        public static final DestinationCardModel PHOENIX_PORTLAND = new DestinationCardModel(Cities.PHOENIX, Cities.PORTLAND, 11);
        public static final DestinationCardModel DALLAS_NEW_YORK = new DestinationCardModel(Cities.DALLAS, Cities.NEW_YORK_CITY, 11);
        public static final DestinationCardModel DENVER_PITTSBURGH = new DestinationCardModel(Cities.DENVER, Cities.PITTSBURGH, 11);
        public static final DestinationCardModel LITTLE_ROCK_WINNIPEG = new DestinationCardModel(Cities.LITTLE_ROCK, Cities.WINNIPEG, 11);

        public static final DestinationCardModel HOUSTON_WINNIPEG = new DestinationCardModel(Cities.HOUSTON, Cities.WINNIPEG, 12);
        public static final DestinationCardModel BOSTON_MIAMI = new DestinationCardModel(Cities.BOSTON, Cities.MIAMI, 12);

        public static final DestinationCardModel SANTA_FE_VANCOUVER = new DestinationCardModel(Cities.SANTA_FE, Cities.VANCOUVER, 13);
        public static final DestinationCardModel CALGARY_PHOENIX = new DestinationCardModel(Cities.CALGARY, Cities.PHOENIX, 13);
        public static final DestinationCardModel MONTREAL_NEW_ORLEANS = new DestinationCardModel(Cities.MONTREAL, Cities.NEW_ORLEANS, 13);
        // 16 POINTS
        public static final DestinationCardModel CHICAGO_LOS_ANGELES = new DestinationCardModel(Cities.CHICAGO, Cities.LOS_ANGELES, 16);

        public static final DestinationCardModel ATLANTA_SAN_FRANCISCO = new DestinationCardModel(Cities.ATLANTA, Cities.SAN_FRANCISCO, 17);
        public static final DestinationCardModel NASHVILLE_PORTLAND = new DestinationCardModel(Cities.NASHVILLE, Cities.PORTLAND, 17);

        public static final DestinationCardModel MONTREAL_VANCOUVER = new DestinationCardModel(Cities.MONTREAL, Cities.VANCOUVER, 20);
        public static final DestinationCardModel LOS_ANGELES_MIAMI = new DestinationCardModel(Cities.LOS_ANGELES, Cities.MIAMI, 20);

        public static final DestinationCardModel LOS_ANGELES_NEW_YORK = new DestinationCardModel(Cities.LOS_ANGELES, Cities.NEW_YORK_CITY, 21);

        public static final DestinationCardModel NEW_YORK_SEATTLE = new DestinationCardModel(Cities.NEW_YORK_CITY, Cities.SEATTLE, 22);

        // full deck is placed in the destination card deck - may be possible to move it to here
    }
}
