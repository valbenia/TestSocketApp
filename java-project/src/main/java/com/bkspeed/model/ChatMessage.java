package com.bkspeed.model;

public class ChatMessage {
	
	private String id;
	private String userId;
	private String content;
	
	public ChatMessage() {}
	
	public ChatMessage(String id, String userId, String content) {
		super();
		this.id = id;
		this.userId = userId;
		this.content = content;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
