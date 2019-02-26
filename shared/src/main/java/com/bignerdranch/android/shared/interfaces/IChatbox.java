package com.bignerdranch.android.shared.interfaces;

import com.bignerdranch.android.shared.models.gameIDModel;

import java.util.List;

import com.bignerdranch.android.shared.models.chatMessageModel;

public interface IChatbox {
	List<chatMessageModel> gameChatMessages = null;
	
	void addMessage(chatMessageModel message);
	List<chatMessageModel> getChats();
}
