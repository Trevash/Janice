package com.bignerdranch.android.shared.resultobjects;

import com.bignerdranch.android.shared.models.gameModel;
import com.bignerdranch.android.shared.models.serverModel;

import java.util.List;

public class GameListData {
    private List<gameModel> games;

    @Deprecated // shared code should not depend on server code - and server model should be server code
    public GameListData(){
        games = serverModel.getInstance().getGames();
    }

    public GameListData(List<gameModel> games) {
        this.games = games;
    }

    public List<gameModel> getGames() {
        return games;
    }
}
