package com.bkspeed.executor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.bkspeed.common.BaseConstant;
import com.bkspeed.common.BaseHelper;
import com.bkspeed.common.BaseUtils;
import com.bkspeed.model.UserDataRealtime;
import com.bkspeed.server.Server;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class WorkerThread extends Thread {
	private DatagramSocket socket;
	private byte[] sendData;
	private byte[] receiveData;
	private DatagramPacket receivePacket;

	public WorkerThread(DatagramSocket socket) {
		this.socket = socket;
		this.sendData = new byte[BaseConstant.MAX_BUFFER_SIZE];
		this.receiveData = new byte[BaseConstant.MAX_BUFFER_SIZE];
		this.receivePacket = new DatagramPacket(receiveData, receiveData.length);
	}

	@Override
	public void run() {
		
		try {
			while(true) {				
				socket.receive(receivePacket);
				String sentence = new String(receivePacket.getData());
				receiveMessage(sentence, receiveData);
			}
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
//			if(socket != null) {
//				socket.close();
//				socket = null;
//			}
//			this.receiveData = null;
//			this.receivePacket = null;
//			this.sendData = null;
			BaseHelper.clearMemory();
		}
	}

	private void receiveMessage(String sentence, byte[] receiveData)
			throws IOException {
		UserDataRealtime userData = BaseUtils.obj.readValue(sentence, UserDataRealtime.class);
		Server.setData(userData.getClientId(), userData, userData.getIsConnected());
		Arrays.fill(receiveData, (byte) 0);
		BaseHelper.printLine("receive data from " + userData.getClientId() + "state :" + userData.getIsConnected());
		sendToClient(receivePacket.getAddress(), receivePacket.getPort());
	}

	private void sendToClient(InetAddress IPAddress, int port) throws IOException {
		Arrays.fill(sendData, (byte)0 );
		HashMap<String, UserDataRealtime> data = Server.getData();
		sendData = BaseUtils.obj.writeValueAsBytes(data);
		
		this.socket.send(new DatagramPacket(sendData, sendData.length, IPAddress, port));
		BaseHelper.printLine("Sending data has " + data.size() + " user " +data.toString());
	}
}