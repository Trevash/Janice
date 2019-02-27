package com.bignerdranch.android.shared.resultobjects;

import com.bignerdranch.android.shared.models.chatboxModel;

public class ChatboxData {
    private chatboxModel chatbox;

    public ChatboxData(chatboxModel chatbox){ this.chatbox = chatbox; }

    public chatboxModel getChatbox() {
        return chatbox;
    }
}
