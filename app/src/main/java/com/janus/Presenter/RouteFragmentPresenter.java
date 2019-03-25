package com.janus.Presenter;

import com.bignerdranch.android.shared.models.abstractRoute;
import com.bignerdranch.android.shared.models.doubleRouteModelFew;
import com.bignerdranch.android.shared.models.doubleRouteModelMany;
import com.bignerdranch.android.shared.models.singleRouteModel;
import com.bignerdranch.android.shared.requestObjects.ClaimRouteRequest;
import com.bignerdranch.android.shared.resultobjects.Results;
import com.janus.ClientFacade;
import com.janus.ClientModel;
import com.janus.Communication.ClaimRouteTask;

import java.util.ArrayList;
import java.util.List;

public class RouteFragmentPresenter implements ClaimRouteTask.Caller, ClientFacade.Presenter{

    public interface View {
        void updateRoutes(List<singleRouteModel> routes);
    }
    private View view;
    private ClientFacade facade = ClientFacade.getInstance();

    public RouteFragmentPresenter(View view) {
        this.view = view;
    }

    //Converts doubleRoutes into singleRoutes for display
    //Todo: Add functionality so that only routes that you have cards for appear
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
        view.updateRoutes(simplifiedList);
    }

    public void claimRoute(singleRouteModel route){
        ClaimRouteTask task = new ClaimRouteTask(this);
        ClaimRouteRequest request = new ClaimRouteRequest(
                facade.getUser().getAuthToken(),
                facade.getGame().getGameID(),
                facade.getGame().getPlayerByUsername(facade.getUser().getUserName()).getId(),
                route.getTrainColor(),
                route
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
