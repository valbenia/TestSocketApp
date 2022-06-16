package com.bkspeed.executor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

import com.bkspeed.common.BaseConstant;
import com.bkspeed.common.BaseHelper;
import com.bkspeed.common.BaseUtils;
import com.bkspeed.model.MessageSocket;
import com.bkspeed.model.Room;
import com.fasterxml.jackson.databind.deser.Deserializers.Base;
import com.fasterxml.jackson.databind.util.JSONPObject;

public class WorkerThreadListener extends Thread {
	
	private Socket socket;
	private DataOutputStream dataOutputStream;
	private DataInputStream dataInputStream;

	public WorkerThreadListener(Socket socket) {
		this.socket = socket;
		try {
			this.dataInputStream = new DataInputStream(this.socket.getInputStream());
			this.dataOutputStream = new DataOutputStream(this.socket.getOutputStream());

		} catch (IOException e) {
			System.err.println("Request Processing Error: ");
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		System.out.println("Processing: " + socket);

		try {
			handleReceiveMessage();
		} catch (IOException e) {
			
			System.err.println("Request Processing Error: " + e);
			BaseHelper.clearMemory();
			e.printStackTrace();
		}

		BaseHelper.printLine("Complete process " + socket);
	}

	private void handleReceiveMessage() throws IOException {

		while(true) {			
			String message = BaseHelper.getMessageInputStream(this.dataInputStream);
			if (message.isEmpty()) {
				throw new IOException("message is empty");
			}
			BaseHelper.printLine("Receive message: "+ message);
			controllMessage(message);
		
		}

	}
	
	private void controllMessage(String message) throws IOException {
		
		MessageSocket mess = BaseUtils.obj.readValue(message.getBytes(), MessageSocket.class);
		BaseHelper.printLine("action: " + mess.getAction() + " value: " + mess.getValue());
		if(Objects.isNull(mess)) {
			throw new IOException("message is empty");
		}
		
		switch (mess.getAction()) {
		case BaseConstant.SOCKET_EVENT_CREATE_ROOM:
			Room tempRoom = BaseUtils.obj.readValue(mess.getValue().getBytes(), Room.class);
			
			Room room = new Room(tempRoom.getId(), tempRoom.getPassword(), tempRoom.getIdMaster());
			WorkerRoomThread handler = new WorkerRoomThread(room);
			BaseUtils.executor.execute(handler);
			BaseUtils.listRoom.add(room);
			
			HashMap<String, Integer> value = new HashMap<String, Integer>();
			value.put("portRoom", room.getPortRoom());
			value.put("portData", room.getPortData());
			
			MessageSocket rep= new MessageSocket(BaseConstant.SOCKET_EVENT_CREATE_ROOM_SUCCESS, BaseUtils.obj.writeValueAsString(value));
			String text = BaseUtils.obj.writeValueAsString(rep);
			BaseHelper.printLine("Text "+ text);
			sendSocketMessage(text);
			break; 

		default:
			break;
		}
		
	}

	private void sendSocketMessage(String message) throws IOException {
		this.dataOutputStream.write(message.getBytes());
		this.dataOutputStream.flush();
	}
	
	private void sendSocketMessage(byte[] message) throws IOException {
		this.dataOutputStream.write(message);
		this.dataOutputStream.flush();
	}

}