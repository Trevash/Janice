package com.bignerdranch.android.shared.resultobjects;

import com.bignerdranch.android.shared.models.gameModel;
import com.bignerdranch.android.shared.models.serverModel;

import java.util.List;

public class GameListData {
    private List<gameModel> games;
    public GameListData(){
        games = serverModel.getInstance().getGames();
    }

    public List<gameModel> getGames() {
        return games;
    }
}
