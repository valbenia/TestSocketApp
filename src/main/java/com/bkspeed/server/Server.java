package com.bkspeed.server;
import java.net.DatagramSocket;
import java.util.HashMap;

import com.bkspeed.common.BaseConstant;
import com.bkspeed.common.BaseHelper;
import com.bkspeed.common.BaseUtils;
import com.bkspeed.executor.WorkerThread;
import com.bkspeed.model.UserDataRealtime;

public class Server {
	
	static HashMap<String, UserDataRealtime> data;
	static DatagramSocket serverSocket;
	

    public static void main(String args[]) throws Exception {
    	
    	data = new HashMap<String, UserDataRealtime>();
    	
        serverSocket = new DatagramSocket(7777);
        BaseHelper.printLine("Binding on ip " + BaseConstant.SERVER_PORT + " port " + BaseConstant.SERVER_PORT);
        
        WorkerThread workerThread = new WorkerThread(serverSocket);
        BaseUtils.executor.execute(workerThread);
     
    }

 	public static synchronized void setData(String Key, UserDataRealtime value, Boolean state) {
		if(!state) {
			data.remove(Key);
		} else {			
			data.put(Key, value);
		}
	}
	
	public static HashMap<String, UserDataRealtime> getData() {
		return data;
	}
}
