package server;
import java.util.List;

public class UserData {
	
    public String clientID;
    public Boolean state;
    public List<Float> position;
    public List<Float> rotation;
    public List<Float> scale;
    
    public UserData() {
    
    }
    
    public UserData(String id, Boolean state, List<Float> pos, List<Float> rot, List<Float> scale) {
    	this.clientID = id;
    	this.state = state;
    	this.position = pos;
    	this.rotation = rot;
    	this.scale = scale;
    }
    public String getClientID() {
		return clientID;
	}


	public void setClientID(String clientID) {
		this.clientID = clientID;
	}


	public Boolean getState() {
		return state;
	}


	public void setState(Boolean state) {
		this.state = state;
	}


	public List<Float> getPosition() {
		return position;
	}


	public void setPosition(List<Float> position) {
		this.position = position;
	}


	public List<Float> getRotation() {
		return rotation;
	}


	public void setRotation(List<Float> rotation) {
		this.rotation = rotation;
	}


	public List<Float> getScale() {
		return scale;
	}


	public void setScale(List<Float> scale) {
		this.scale = scale;
	}
	
}