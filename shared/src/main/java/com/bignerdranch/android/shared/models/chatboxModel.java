package com.bignerdranch.android.shared.models;

import java.util.ArrayList;

import com.bignerdranch.android.shared.interfaces.IChatbox;

public class chatboxModel implements IChatbox {

	ArrayList<chatMessageModel> gameChatMessages = new ArrayList<chatMessageModel>();
	
	public chatboxModel() {
		
	}
	@Override
	public void addMessage(chatMessageModel message) {
		gameChatMessages.add(message);
	}

	@Override
	public ArrayList<chatMessageModel> getChats() {
		return gameChatMessages;
	}

}
