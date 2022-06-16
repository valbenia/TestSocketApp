package com.bkspeed.model;

public class UserDataRealtime {
	
    private String clientId;
    private Boolean isConnected;
    private ClientTransform clientTransform;
//    private String item1;
//    private String item2;
    
    public UserDataRealtime() {
    
    }
    

	public UserDataRealtime(String clientID, Boolean state, ClientTransform clientTransform) {
		super();
		this.clientId = clientID;
		this.isConnected = state;
		this.clientTransform = clientTransform;
//		this.item1 = null;
//		this.item2 = null;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientID) {
		this.clientId = clientID;
	}

	public Boolean getIsConnected() {
		return isConnected;
	}

	public void setIsConnected(Boolean state) {
		this.isConnected = state;
	}

	public ClientTransform getClientTransform() {
		return clientTransform;
	}

	public void setClientTransform(ClientTransform clientTransform) {
		this.clientTransform = clientTransform;
	}
    
    
}