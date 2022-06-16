package com.bkspeed.server;

import java.net.SocketException;

import com.bkspeed.common.BaseUtils;
import com.bkspeed.executor.WorkerRoomThread;
import com.bkspeed.model.Room;

public class ServerMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Room room = new Room(1,123,"huy");
			WorkerRoomThread handler = new WorkerRoomThread(room);
			BaseUtils.executor.execute(handler);
			System.out.println(room.getId());
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
