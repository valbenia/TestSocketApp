package UDPsocket;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import server.UserData;
public class serverUDP {
	
	static HashMap<String, UserData> data;
	static ObjectMapper obj;
	static DatagramSocket serverSocket;
	static byte[] sendData;

    public static void main(String args[]) throws Exception {
    	DatagramPacket sendPacket;
    	DatagramPacket receivePacket;
    	data = new HashMap<String, UserData>();
    	obj = new ObjectMapper();
        //Tạo socket phía server với số hiệu cổng 1108
        serverSocket = new DatagramSocket(2222);
        System.out.println("Binding on port: 2222");
        //tạo biến receiveData để nhận dữ liệu từ gói tin đến
        byte[] receiveData = new byte[1024];
        //tạo sendData để nhận dữ liệu gửi lên gói tin đi
        sendData  = new byte[1024];
        
        
        receivePacket = new DatagramPacket(receiveData, receiveData.length);
        
        while(true) {
        	
            //nhận gói tin qua phương thức receive()
            serverSocket.receive(receivePacket);
            
            //Chuyển dữ liệu vừa nhận về dạng String
            String sentence = new String(receivePacket.getData());
            receiveMessage(sentence, receiveData);
          
            //Lấy địa chỉ IP của bên gửi
            InetAddress IPAddress = receivePacket.getAddress();
            //Lấy số hiệu cổng bên gửi
            int port = receivePacket.getPort();
            
            //Xử lý dữ liệu vừa nhận
            String sentence_to_client = sentence;
            sendToClient(IPAddress, port);
            
            //clear Data;
            Arrays.fill(receiveData, (byte)0);
            Arrays.fill(sendData, (byte)0 );
        }
    }

 	private static void sendToClient(InetAddress iPAddress, int port) {
		try {
			Arrays.fill(sendData, (byte)0 );
			sendData = obj.writeValueAsBytes(data);
			serverSocket.send(new DatagramPacket(sendData, sendData.length, iPAddress, port));
			System.out.println("Sending data: " + data.toString());
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void receiveMessage(String sentence, byte[] receiveData) {
		try {
			UserData userData = obj.readValue(sentence, UserData.class);
			data.put(userData.clientID, userData);
//			System.out.print("receiveMessage: " + data);
			Arrays.fill(receiveData, (byte)0);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
