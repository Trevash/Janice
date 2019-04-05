package com.bignerdranch.android.shared;

import com.bignerdranch.android.shared.models.DestinationCardModel;
import com.bignerdranch.android.shared.models.cityModel;
import com.bignerdranch.android.shared.models.colors.cardColorEnum;
import com.bignerdranch.android.shared.models.trainCardModel;

/**
 * A class of constants that are intended for use throughout the project, including in the server
 */
public class Constants {
    public static final String IP_ADDRESS =
            //"192.168.1.100"; // Jason's home IP address
            //"192.168.254.216";
            "192.168.254.186";
            //"10.37.93.67"; // Regular IP address for group testing
            //"10.34.241.184"; // another IP address for group testing
    public static final int PORT = 8087;

    //1 map depicting the United States
    //240 Colored Train Cars (48 each in Blue, Red, Green, Yellow & Black)
    //96 Train Cards (12 each in Red, Orange, Yellow, Green, Blue, Purple, Black & White)
    //14 Locomotive Wild Cards (Multicolored)


    public static class Commands {
        // Client commands
        public static final String LOGIN = "Login";
        public static final String REGISTER = "Register";
        public static final String GAME_LIST = "GameList";
        public static final String CREATE = "Create";
        public static final String CLAIM_ROUTE = "ClaimRoute";
        public static final String JOIN = "Join";
        public static final String START = "Start";
        public static final String RETURN_DESTINATION_CARDS = "ReturnDestinationCards";
        public static final String UPDATE_CHAT = "UpdateChat";
        public static final String UPDATE_GAME_STATUS = "UpdateGameStatus";
        public static final String DRAW_FIRST_TRAIN_CARD = "DrawFirstTrainCard";
        public static final String DRAW_SECOND_TRAIN_CARD = "DrawSecondTrainCard";
        public static final String STATS = "Stats";
        public static final String LAST_ROUND = "LastRound";
        public static final String END_GAME = "EndGame";

        // server commands
        public static final String DRAW_DESTINATION_CARDS = "DrawDestinationCards";

    }
    public static class TrainCards {
        // 12 of each color
        public static final trainCardModel RED = new trainCardModel(cardColorEnum.RED);
        public static final trainCardModel ORANGE = new trainCardModel(cardColorEnum.ORANGE);
        public static final trainCardModel YELLOW = new trainCardModel(cardColorEnum.YELLOW);
        public static final trainCardModel GREEN = new trainCardModel(cardColorEnum.GREEN);
        public static final trainCardModel BLUE = new trainCardModel(cardColorEnum.BLUE);
        public static final trainCardModel PURPLE = new trainCardModel(cardColorEnum.PURPLE);
        public static final trainCardModel BLACK = new trainCardModel(cardColorEnum.BLACK);
        public static final trainCardModel WHITE = new trainCardModel(cardColorEnum.WHITE);
        // 14 locomotives
        public static final trainCardModel LOCOMOTIVE = new trainCardModel(cardColorEnum.LOCOMOTIVE);
    }

    public static class Cities {
        // TODO have someone check over this list for completion
        public static final cityModel ATLANTA = new cityModel("Atlanta", 888, 502);
        public static final cityModel BOSTON = new cityModel("Boston", 1080, 208);
        public static final cityModel CALGARY = new cityModel("Calgary", 350, 56);
        public static final cityModel CHARLESTON = new cityModel("Charleston", 994, 512);
        public static final cityModel CHICAGO = new cityModel("Chicago", 774, 302);
        public static final cityModel DALLAS = new cityModel("Dallas", 608, 564);

        public static final cityModel DENVER = new cityModel("Denver", 444, 390);
        public static final cityModel DULUTH = new cityModel("Duluth", 662, 234);
        public static final cityModel EL_PASO = new cityModel("El Paso", 412, 590);
        public static final cityModel HELENA = new cityModel("Helena", 386, 218);
        public static final cityModel HOUSTON = new cityModel("Houston", 648, 648);

        public static final cityModel KANSAS_CITY = new cityModel("Kansas City", 630, 390);
        public static final cityModel LAS_VEGAS = new cityModel("Las Vegas", 218, 450);
        public static final cityModel LITTLE_ROCK = new cityModel("Little Rock", 706, 492);
        public static final cityModel LOS_ANGELES = new cityModel("Los Angeles", 150, 516);
        public static final cityModel MIAMI = new cityModel("Miami", 1000, 714);

        public static final cityModel MONTREAL = new cityModel("Montreal", 1004, 134);
        public static final cityModel NASHVILLE = new cityModel("Nashville", 840, 452);
        public static final cityModel NEW_ORLEANS = new cityModel("New Orleans", 766, 620);
        public static final cityModel NEW_YORK_CITY = new cityModel("New York City", 1028, 250);
        public static final cityModel OKLAHOMA_CITY = new cityModel("Oklahoma City", 588, 474);
        public static final cityModel OMAHA = new cityModel("Omaha", 612, 326);


        public static final cityModel PHOENIX  = new cityModel("Phoenix", 276, 516);
        public static final cityModel PITTSBURGH = new cityModel("Pittsburgh", 948, 308);
        public static final cityModel PORTLAND = new cityModel("Portland", 134, 188);
        public static final cityModel RALEIGH = new cityModel("Raleigh", 974, 430);
        public static final cityModel SALT_LAKE_CITY = new cityModel("Salt Lake City", 296, 328);
        public static final cityModel SANTA_FE = new cityModel("Santa Fe", 408, 496);

        public static final cityModel SAN_FRANCISCO = new cityModel("San Francisco", 74, 368);
        public static final cityModel ST_MARIE = new cityModel("Sault St. Marie", 802, 178);
        public static final cityModel ST_LOUIS = new cityModel("St. Louis", 734, 390);
        public static final cityModel SEATTLE = new cityModel("Seattle", 162, 132);
        public static final cityModel TORONTO = new cityModel("Toronto", 932, 202);
        public static final cityModel VANCOUVER = new cityModel("Vancouver", 200, 80);
        public static final cityModel WASHINGTON = new cityModel("Washington", 1030, 370);
        public static final cityModel WINNIPEG = new cityModel("Winnipeg", 550, 80);

        public static final cityModel[] CITIES = {
                ATLANTA, BOSTON, CALGARY, CHARLESTON, CHICAGO, DALLAS, DENVER, DULUTH, EL_PASO,
                HELENA, HOUSTON, KANSAS_CITY, LAS_VEGAS, LITTLE_ROCK, LOS_ANGELES, MIAMI, MONTREAL,
                NASHVILLE, NEW_ORLEANS, NEW_YORK_CITY, OKLAHOMA_CITY, OMAHA, PHOENIX, PITTSBURGH,
                PORTLAND, RALEIGH, SALT_LAKE_CITY, SANTA_FE, SAN_FRANCISCO, ST_MARIE, ST_LOUIS,
                SEATTLE, TORONTO, VANCOUVER, WASHINGTON, WINNIPEG
        };

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
