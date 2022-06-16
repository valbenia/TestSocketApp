package com.bkspeed.model;

import java.util.Arrays;
import java.util.List;

public class MessageSocket {
	
	private String action;
	
	private String value;
	
	public MessageSocket() {}
	
	public MessageSocket(String text) {
		List<String> lst = Arrays.asList(text.split("|")); 
		this.action = lst.get(0);
		this.action = lst.get(1);
	}
	
	public MessageSocket(String action, String value) {
		this.action = action;
		this.value = value;
	}
	
//	public String getMessage() {
//		return this.action + "|" + this.value;
//	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public static MessageSocket builer(String action, String value) {
		return new MessageSocket(action, value);
	}

}
