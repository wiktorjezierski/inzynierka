package actions;

import java.io.Serializable;
import java.util.List;

import database.User;

public class Response implements Serializable {
	
	private static final long serialVersionUID = -8662492570050468270L;
	
	private List<User> users;
	private boolean confirmation;
	private String value;
	private DeviceType deviceType;

	public Response(boolean confirmation) {
		this.confirmation = confirmation;
	}

	public Response(List<User> users) {
		this.confirmation = true;
		this.users = users;
	}
	public Response(List<User> users, String sessionID) {
		this.confirmation = true;
		this.users = users;
		value = sessionID;
	}
	
	public Response(String addressIP, DeviceType deviceType) {
		this.value = addressIP;
		this.deviceType = deviceType;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public boolean isConfirmation() {
		return confirmation;
	}

	public void setConfirmation(boolean confirmation) {
		this.confirmation = confirmation;
	}

	public String getAddressIP() {
		return value;
	}

	public DeviceType getDeviceType() {
		return deviceType;
	}
	
}
