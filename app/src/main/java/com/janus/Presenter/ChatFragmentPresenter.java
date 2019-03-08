package com.janus.Presenter;

import com.bignerdranch.android.shared.models.chatMessageModel;
import com.bignerdranch.android.shared.requestObjects.UpdateChatboxRequest;
import com.bignerdranch.android.shared.resultobjects.Results;
import com.janus.ClientFacade;
import com.janus.Communication.UpdateChatTask;

import java.util.List;

public class ChatFragmentPresenter implements ClientFacade.Presenter, UpdateChatTask.Caller {

    @Override
    public void onError(String s) {

    }

    @Override
    public void onCreateComplete(Results r) {

    }

    public interface View {
        void updateSendButton(boolean isActive);
        void updateUI();
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
        UpdateChatboxRequest request = new UpdateChatboxRequest(
                facade.getGame().getGameID(),
                facade.getUser().getAuthToken(),
                new chatMessageModel(
                        facade.getUser().getUserName(),
                        this.chatMessage
                )
        );
        UpdateChatTask updateChatTask = new UpdateChatTask(this);
        updateChatTask.execute(request);
    }

    public void setFragment() {
        facade.setPresenter(this);
    }

    public List<chatMessageModel> getChats() {
        return facade.getChatbox().getChats();
    }

    public void updateUI() {
        view.updateUI();
    }

}
