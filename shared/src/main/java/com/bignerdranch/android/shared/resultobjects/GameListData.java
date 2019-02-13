package com.bignerdranch.android.shared.resultobjects;

import com.bignerdranch.android.shared.models.gameModel;

import java.util.List;

public class GameListData {
    private List<gameModel> games;

    public GameListData(List<gameModel> games) {
        this.games = games;
    }

    public List<gameModel> getGames() {
        return games;
    }
}
