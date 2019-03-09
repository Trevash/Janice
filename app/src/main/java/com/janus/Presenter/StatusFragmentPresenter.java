package com.janus.Presenter;

import com.bignerdranch.android.shared.models.playerModel;
import com.bignerdranch.android.shared.models.userModel;
import com.janus.ClientFacade;

import java.util.List;

public class StatusFragmentPresenter {

    private View view;
    private ClientFacade facade = ClientFacade.getInstance();

    public interface View {

    }

    public StatusFragmentPresenter(View v) {
        this.view = v;
    }

    public List<int[]> getStats() {
        return facade.getStats();
    }

    public List<playerModel> getPlayers() {
        return facade.getPlayers();
    }

    public userModel getCurrentPlayer() {
        return facade.getUser();
    }
}
