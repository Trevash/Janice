package com.janus.Presenter;

import com.janus.ClientFacade;

public class ChatFragmentPresenter implements ClientFacade.Presenter {

    public interface View {
        void updateSendButton(boolean isActive);
        //void displayLoginError(String message);
        //void displayLoginSuccess();
    }

    private ClientFacade facade = ClientFacade.getInstance();
    private String chatMessage = "";
    private View view;

    public ChatFragmentPresenter(View view) {
        this.view = view;
    }

    public void updateChatMessage(String message) {
        this.chatMessage = message;
        if (!chatMessage.equals("")) {
            view.updateSendButton(true);
        }
    }

    public void sendClicked() {
        view.updateSendButton(false);
    }

    public void setFragment() {
        facade.setPresenter(this);
    }

    public void updateUI() {

    }

}
