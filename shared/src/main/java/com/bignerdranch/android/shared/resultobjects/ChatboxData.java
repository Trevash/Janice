package com.bignerdranch.android.shared.resultobjects;

import com.bignerdranch.android.shared.models.chatboxModel;
import com.bignerdranch.android.shared.models.gameIDModel;

public class ChatboxData {
    private chatboxModel chatbox;
    private gameIDModel gameID;

    public ChatboxData(chatboxModel chatbox, gameIDModel gameID){
        this.chatbox = chatbox;
        this.gameID = gameID;
    }

    public chatboxModel getChatbox() {
        return chatbox;
    }

    public gameIDModel getGameID() {
        return gameID;
    }
}
