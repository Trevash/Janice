package com.bignerdranch.android.shared.models;

import com.bignerdranch.android.shared.interfaces.IMessage;

public class chatMessageModel implements IMessage {

	private usernameModel username;
	private String message;
	
	public chatMessageModel(usernameModel u, String m) {
		username = u;
		message = m;
	}
	
	@Override
	public usernameModel getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}

}
