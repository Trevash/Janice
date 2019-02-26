package com.bignerdranch.android.shared.requestObjects;

import com.bignerdranch.android.shared.models.authTokenModel;
import com.bignerdranch.android.shared.models.chatboxModel;
import com.bignerdranch.android.shared.models.gameIDModel;
import com.bignerdranch.android.shared.models.gameModel;

public class UpdateChatboxRequest {
	private gameIDModel gameID;
	private authTokenModel auth;
	private chatboxModel chatbox;
	
	public UpdateChatboxRequest(gameIDModel game, authTokenModel auth, chatboxModel chatbox) {
		this.gameID = game;
		this.auth = auth;
		this.chatbox = chatbox;
	}
	
	public gameIDModel getGameID() {
		return gameID;
	}
    
	public authTokenModel getAuth() {
		return auth;
	}
	
	public chatboxModel getChatbox() {
		return chatbox;
	}
}
