package com.bkspeed.model;

import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.bkspeed.common.BaseHelper;

public class Room {
	
	private int id;
	private Integer password;
	private Boolean isWaiting;
	private String idMaster;
	
	private HashMap<String, UserDataRealtime> userDataMap;
//	private HashMap<String, String> iteamDataMap;
	
//	private List<User> userList;
	private List<ChatMessage> chatting;
//	
	private int portData;	// only receive
	private int portRoom;	// only send
	
	public Room() {}
	
	public Room(int id, Integer password , String idMaster) throws SocketException {
		super();
		this.id = id;
		this.password = password;
		this.isWaiting = true;
		this.idMaster = idMaster;
//		this.userList =  Arrays.asList(new User());
		this.userDataMap = new HashMap<String, UserDataRealtime>();
		this.chatting = new ArrayList<ChatMessage>();
		this.portRoom = BaseHelper.randomPort();
		this.portData = BaseHelper.randomPort();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getPassword() {
		return password;
	}

	public void setPassword(Integer password) {
		this.password = password;
	}

	public Boolean getIsWaiting() {
		return isWaiting;
	}

	public void setIsWaiting(Boolean isWaiting) {
		this.isWaiting = isWaiting;
	}

	public String getIdMaster() {
		return idMaster;
	}

	public void setIdMaster(String idMaster) {
		this.idMaster = idMaster;
	}

	public HashMap<String, UserDataRealtime> getUserDataMap() {
		return userDataMap;
	}

	public void setUserDataMap(HashMap<String, UserDataRealtime> userDataMap) {
		this.userDataMap = userDataMap;
	}

	public List<ChatMessage> getChatting() {
		return chatting;
	}

	public void setChatting(List<ChatMessage> chatting) {
		this.chatting = chatting;
	}

	public int getPortData() {
		return portData;
	}

	public void setPortData(int portData) {
		this.portData = portData;
	}

	public int getPortRoom() {
		return portRoom;
	}

	public void setPortRoom(int portRoom) {
		this.portRoom = portRoom;
	}
	
	
}
