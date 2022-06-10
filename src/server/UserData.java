package server;
import java.util.List;

public class UserData {
	
    public String clientID;
    public Boolean state;
    public ClientTransform clientTransform;
    
    public UserData() {
    
    }
    
    public UserData(String id, Boolean state) {
    	this.clientID = id;
    	this.state = state;

    }

	
}