package com.janus.Presenter;

import com.bignerdranch.android.shared.interfaces.IServer;
import com.bignerdranch.android.shared.models.gameModel;
import com.bignerdranch.android.shared.models.usernameModel;
import com.bignerdranch.android.shared.requestObjects.StartGameRequest;
import com.janus.ClientFacade;
import com.janus.Communication.ServerProxy;

public class LobbyFragmentPresenter implements ClientFacade.Presenter {

    private ClientFacade facade = ClientFacade.getInstance();
    private IServer sp = ServerProxy.getInstance();

    public interface View {
        void updateLobbyButtons(boolean isActive);
        void updateLobbyUI(gameModel game);
        void displayLobbyError(String message);
        void displayLobbySuccess();
    }

    public void setFragment() {
        facade.setPresenter(this);
    }

    private LobbyFragmentPresenter.View view;
    //private Player[] players;

    public LobbyFragmentPresenter(View v) {
        this.view = v;
    }


    public void startGameClicked() {
        try {
            sp.startGame(new StartGameRequest(facade.getGame().getGameID(), facade.getUser().getAuthToken()));
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void readyClicked(){
        //Change ready/not ready
    }

    /*public void updatePlayerList(Player[] p) {
        this.players = p;
        checkButtons();
    }*/

    private void checkButtons() {
        //Check to make sure everybody is ready and that there are at least 2 players
        view.updateLobbyButtons(true);
    }

    public usernameModel getUsername(){
        return facade.getUser().getUserName();
    }

    public void updateUI() {
        gameModel g = facade.getGame();
        view.updateLobbyUI(g);
    }

    public Boolean isModelEmpty(){
        return facade.getGame() == null;
    }
}
