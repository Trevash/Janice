package com.janus.Presenter;

import com.bignerdranch.android.shared.models.abstractRoute;
import com.janus.ClientFacade;
import com.janus.ClientModel;

import java.util.List;

public class RouteFragmentPresenter implements ClientFacade.Presenter{
    public interface View {
        void updateRoutes(List<abstractRoute> routes);
    }
    private View view;
    private ClientFacade facade = ClientFacade.getInstance();
    private ClientModel model = ClientModel.getInstance();

    public RouteFragmentPresenter(View view) {
        this.view = view;
    }

    public void updateUI(){
        view.updateRoutes(model.getGame().getRoutes());
    }

    void claimRoute(String routeID){
        //Create a claimRoute task?
        //Create claimRouteRequest?
        //claimRouteTask.execute(claimRouteRequest);
    }

    public void setFragment() {
        facade.setPresenter(this);
    }

}
