package com.bkspeed.server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.bkspeed.common.BaseConstant;
import com.bkspeed.common.BaseUtils;
import com.bkspeed.executor.WorkerThreadListener;

public class EchoMultiServer {


	public static void main(String[] args) throws IOException {
		
		ServerSocket serverSocket = null;
		try {
			System.out.println("Binding to port " + BaseConstant.SERVER_PORT + ", please wait  ...");
			serverSocket = new ServerSocket(BaseConstant.SERVER_PORT);
			System.out.println("Server started: " + serverSocket);
			System.out.println("Waiting for a client ...");
			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("Client accepted: " + socket);
				
				WorkerThreadListener handler = new WorkerThreadListener(socket);
				BaseUtils.executor.execute(handler);
				
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			if (serverSocket != null) {
				serverSocket.close();
			}
		}
	}

}