package com.bignerdranch.android.shared.requestObjects;

import com.bignerdranch.android.shared.interfaces.IGameRequest;
import com.bignerdranch.android.shared.models.authTokenModel;
import com.bignerdranch.android.shared.models.chatMessageModel;
import com.bignerdranch.android.shared.models.chatboxModel;
import com.bignerdranch.android.shared.models.gameIDModel;
import com.bignerdranch.android.shared.models.gameModel;

public class UpdateChatboxRequest implements IGameRequest {
	private gameIDModel gameID;
	private authTokenModel auth;
	private chatMessageModel message;
	
	public UpdateChatboxRequest(gameIDModel game, authTokenModel auth, chatMessageModel message) {
		this.gameID = game;
		this.auth = auth;
		this.message = message;
	}
	
	public gameIDModel getGameID() {
		return gameID;
	}
    
	public authTokenModel getAuth() {
		return auth;
	}
	
	public chatMessageModel getMessage() {
		return message;
	}
}
