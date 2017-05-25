package com.xiaoming.dto;

import java.util.ArrayList;

public class UserUnreadDto {

	private long systemMessage;
	private ArrayList<UnreadDto> members;

	public long getSystemMessage() {
		return systemMessage;
	}

	public void setSystemMessage(long systemMessage) {
		this.systemMessage = systemMessage;
	}

	public ArrayList<UnreadDto> getMembers() {
		return members;
	}

	public void setMembers(ArrayList<UnreadDto> members) {
		this.members = members;
	}

}
