package com.bignerdranch.android.shared.interfaces;

import com.bignerdranch.android.shared.models.usernameModel;

public interface IMessage {
	//the user that initially sent the message
	usernameModel username = null;
	//the message being sent
	String message = "";
	
	
	usernameModel getUsername();
	String getMessage();
	
}
