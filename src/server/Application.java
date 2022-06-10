package server;

import java.util.HashMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Application {

	public static void main(String[] args) {

    	String text = "{\"clientID\":\"253719326fa1838b5b9450cabb9dd90fe8518d1d28e1bc536d11e89b5131fc01\",\"state\":false,\"position\":[-7.7491,3.3451,-12.2693],\"rotation\":[0.6326,-0.7456,0.1963,-0.073],\"scale\":[5,5,5]}";
    	ObjectMapper obj = new ObjectMapper();
    	UserData userData;
    	HashMap<String, UserData> data = new HashMap<String, UserData>();
    	
		try {
			userData = obj.readValue(text, UserData.class);
			data.put("test1", userData);
	    	data.put("test2", userData);
	    	String temp = obj.writeValueAsString(data);
			System.out.print("Hello: " + temp);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

}
