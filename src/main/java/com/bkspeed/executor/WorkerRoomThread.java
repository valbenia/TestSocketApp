package com.bkspeed.executor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Arrays;

import com.bkspeed.common.BaseConstant;
import com.bkspeed.common.BaseHelper;
import com.bkspeed.model.MessageSocket;
import com.bkspeed.model.Room;

public class WorkerRoomThread extends Thread {
	
	Room room;
	DatagramSocket socket;
	DatagramPacket receivePacket;
	DatagramPacket sendPacket;
	byte[] sendBuf, receiveBuf;
	
	public WorkerRoomThread(Room room) {
		try {
			this.room = room;
			receiveBuf = new byte[BaseConstant.MAX_BUFFER_SIZE];
			sendBuf = new byte[BaseConstant.MAX_BUFFER_SIZE];
			socket = new DatagramSocket(room.getPortData());
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		receivePacket = new DatagramPacket(receiveBuf, receiveBuf.length);
		try {
			while(true) {
				handleMessageRoom();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void handleMessageRoom() throws IOException {
		socket.receive(receivePacket);
		receiveBuf = receivePacket.getData();
		sendBuf = receiveBuf;
		sendPacket = new DatagramPacket(sendBuf, sendBuf.length, receivePacket.getAddress(), receivePacket.getPort());
		socket.send(sendPacket);
		String newMess = new String(sendBuf);
		BaseHelper.printLine("Reply message " + newMess);
		BaseHelper.printLine("ip: " + receivePacket.getAddress() + " port: " + receivePacket.getPort());
		Arrays.fill(sendBuf, (byte)0);
		Arrays.fill(receiveBuf, (byte)0);
	}
}
