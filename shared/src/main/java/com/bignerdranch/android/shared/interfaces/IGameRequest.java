package com.bignerdranch.android.shared.interfaces;

import com.bignerdranch.android.shared.models.gameIDModel;

public interface IGameRequest {
    gameIDModel gameID = null;

    gameIDModel getGameID();
}
