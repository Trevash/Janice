package com.janus.Presenter;

import com.janus.ClientFacade;

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
}
