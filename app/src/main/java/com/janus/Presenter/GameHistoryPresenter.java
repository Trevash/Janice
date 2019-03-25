package com.janus.Presenter;

import com.bignerdranch.android.shared.models.chatMessageModel;
import com.janus.ClientFacade;

import java.util.List;

public class GameHistoryPresenter implements ClientFacade.Presenter {

    public interface View {
        void updateUI();
    }

    private ClientFacade facade = ClientFacade.getInstance();
    private String chatMessage = "";
    private View view;

    public GameHistoryPresenter(View view) {
        this.view = view;
    }

    public List<chatMessageModel> getHistory() {
        return facade.getHistory().getChats();
    }

    public void setFragment() {
        facade.setPresenter(this);
    }

    public void updateUI() {
        view.updateUI();
    }
}
