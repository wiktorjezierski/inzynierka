package actions;

import java.io.Serializable;
import java.util.List;

import database.User;

public class Response implements Serializable {
	
	private static final long serialVersionUID = -8662492570050468270L;
	
	private List<User> users;
	private boolean confirmation;

	public Response(boolean confirmation) {
		this.confirmation = confirmation;
	}

	public Response(List<User> users) {
		this.confirmation = true;
		this.users = users;
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
}
