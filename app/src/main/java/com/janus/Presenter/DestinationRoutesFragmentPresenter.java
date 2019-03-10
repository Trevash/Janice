package com.janus.Presenter;

import com.bignerdranch.android.shared.models.DestinationCardModel;
import com.janus.ClientFacade;
import com.janus.ClientModel;

import java.util.List;

public class DestinationRoutesFragmentPresenter implements ClientFacade.Presenter {

    public interface View {
        void updateSendButton(boolean isActive);
        //void displayLoginError(String message);
        //void displayLoginSuccess();
    }

    private ClientFacade facade = ClientFacade.getInstance();
    private View view;

    public DestinationRoutesFragmentPresenter(View v) {
        this.view = v;
    }

    public void setFragment() {
        facade.setPresenter(this);
    }

    public void updateUI() {

    }

    public List<DestinationCardModel> getDestinationCards() {
        return ClientModel.getInstance().getGame()
                .getPlayerByID(ClientModel.getInstance().getUser().getUserName())
                .getDestinationCardHand();
    }
}
