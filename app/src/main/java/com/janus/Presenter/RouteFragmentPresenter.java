package com.janus.Presenter;

import com.bignerdranch.android.shared.models.abstractRoute;
import com.bignerdranch.android.shared.models.colors.cardColorEnum;
import com.bignerdranch.android.shared.models.colors.routeColorEnum;
import com.bignerdranch.android.shared.models.doubleRouteModelFew;
import com.bignerdranch.android.shared.models.doubleRouteModelMany;
import com.bignerdranch.android.shared.models.singleRouteModel;
import com.bignerdranch.android.shared.models.trainCardModel;
import com.bignerdranch.android.shared.models.usernameModel;
import com.bignerdranch.android.shared.requestObjects.ClaimRouteRequest;
import com.bignerdranch.android.shared.resultobjects.Results;
import com.janus.ClientFacade;
import com.janus.ClientModel;
import com.janus.Communication.ClaimRouteTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RouteFragmentPresenter implements ClaimRouteTask.Caller, ClientFacade.Presenter{

    public interface View {
        void updateRoutes(List<singleRouteModel> routes);
    }
    private View view;
    private ClientFacade facade = ClientFacade.getInstance();
    private ClientModel model = ClientModel.getInstance();

    public RouteFragmentPresenter(View view) {
        this.view = view;
    }

    //Converts doubleRoutes into singleRoutes for display
    public void updateUI(){
        List<abstractRoute> routes = facade.getGame().getRoutes();
        ArrayList<singleRouteModel> simplifiedList = new ArrayList<>();
        for (abstractRoute element : routes) {
            if(element.getClass().equals(singleRouteModel.class)){
                singleRouteModel route = (singleRouteModel) element;
                if(route.claimable()) {
                    simplifiedList.add(route);
                }
            }
            else if(element.getClass().equals(doubleRouteModelFew.class)){
                doubleRouteModelFew doubleRoute = (doubleRouteModelFew) element;
                if(doubleRoute.claimable()){
                    singleRouteModel route1 =
                            new singleRouteModel(doubleRoute.getCity1(), doubleRoute.getCity2(),
                                    doubleRoute.getLength(), doubleRoute.getTrainColor1(), doubleRoute.getRouteID());
                    singleRouteModel route2 =
                            new singleRouteModel(doubleRoute.getCity1(), doubleRoute.getCity2(),
                                    doubleRoute.getLength(), doubleRoute.getTrainColor2(), doubleRoute.getRouteID());
                    simplifiedList.add(route1);
                    simplifiedList.add(route2);
                }
            } else { //doubleRouteModelMany
                doubleRouteModelMany doubleRoute = (doubleRouteModelMany) element;
                if(doubleRoute.claimableRoute1()){
                    singleRouteModel route1 =
                            new singleRouteModel(doubleRoute.getCity1(), doubleRoute.getCity2(),
                                    doubleRoute.getLength(), doubleRoute.getTrainColor1(), doubleRoute.getRouteID());
                    simplifiedList.add(route1);
                }
                if(doubleRoute.claimableRoute2()){
                    singleRouteModel route2 =
                            new singleRouteModel(doubleRoute.getCity1(), doubleRoute.getCity2(),
                                    doubleRoute.getLength(), doubleRoute.getTrainColor2(), doubleRoute.getRouteID());
                    simplifiedList.add(route2);
                }
            }
        }
        simplifiedList = filterRoutesByCost(simplifiedList);
        view.updateRoutes(simplifiedList);
    }

    private ArrayList<singleRouteModel> filterRoutesByCost(ArrayList<singleRouteModel> routes){
        ArrayList<singleRouteModel> updatedList = new ArrayList<>();
        usernameModel playerUsername = model.getUser().getUserName();
        List<trainCardModel> playerHand = model.getGame().getPlayerByUsername(playerUsername).getTrainCardHand();
        int numCards = 0;
        for (singleRouteModel element : routes) {
            routeColorEnum color = element.getTrainColor();
            switch(color)
            {
                case RED :
                    numCards = getNumCardsOfColor(playerHand, cardColorEnum.RED);
                    break;
                case ORANGE:
                    numCards = getNumCardsOfColor(playerHand, cardColorEnum.ORANGE);
                    break;
                case YELLOW:
                    numCards = getNumCardsOfColor(playerHand, cardColorEnum.YELLOW);
                    break;
                case BLUE:
                    numCards = getNumCardsOfColor(playerHand, cardColorEnum.BLUE);
                    break;
                case GREEN:
                    numCards = getNumCardsOfColor(playerHand, cardColorEnum.GREEN);
                    break;
                case PURPLE:
                    numCards = getNumCardsOfColor(playerHand, cardColorEnum.PURPLE);
                    break;
                case BLACK:
                    numCards = getNumCardsOfColor(playerHand, cardColorEnum.BLACK);
                    break;
                case WHITE:
                    numCards = getNumCardsOfColor(playerHand, cardColorEnum.WHITE);
                    break;
                default : //Gray
                    numCards = getMaxCardsOfOneColor(playerHand);
            }
            if(numCards >= element.getLength()){
                updatedList.add(element);
            }
        }
        return updatedList;
    }

    private int getMaxCardsOfOneColor(List<trainCardModel> cards){
        Map<cardColorEnum, Integer> numCardsOfColor = new HashMap<>();
        for(trainCardModel card : cards) {
            cardColorEnum color = card.getColor();
            if(numCardsOfColor.containsKey(color)){
                numCardsOfColor.put(color, numCardsOfColor.get(color) + 1);
            } else {
                numCardsOfColor.put(color, 1);
            }
        }

        int maxValue = 0;

        Iterator it = numCardsOfColor.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry colorNum = (Map.Entry)it.next();
            int currentValue = (int) colorNum.getValue();
            if(currentValue > maxValue){
                maxValue = currentValue;
            }
            it.remove(); // avoids a ConcurrentModificationException
        }

        int numLocomotiveCards = 0;
        if(numCardsOfColor.containsKey(cardColorEnum.LOCOMOTIVE)){
            numLocomotiveCards = numCardsOfColor.get(cardColorEnum.LOCOMOTIVE);
        }

        return numLocomotiveCards + maxValue;
    }

    private int getNumCardsOfColor(List<trainCardModel> cards, cardColorEnum color){
        int numCards = 0;
        for (trainCardModel card : cards) {
            if(card.getColor().equals(color)){
                numCards++;
            }
            if(card.getColor().equals(cardColorEnum.LOCOMOTIVE)) {
                numCards++;
            }
        }
        return numCards;
    }

    public void claimRoute(singleRouteModel route){
        ClaimRouteTask task = new ClaimRouteTask(this);
        ClaimRouteRequest request = new ClaimRouteRequest(
                facade.getUser().getAuthToken(),
                facade.getGame().getGameID(),
                facade.getGame().getPlayerByUsername(facade.getUser().getUserName()).getId(),
                route.getTrainColor(),
                route.getRouteID()
        );
        task.execute(request);
    }

    @Override
    public void onError(String s) {

    }

    @Override
    public void onClaimRouteComplete(Results r) {

    }

    public void setFragment() {
        facade.setPresenter(this);
    }

}
