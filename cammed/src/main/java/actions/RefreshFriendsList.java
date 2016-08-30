package actions;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import database.User;

public class RefreshFriendsList implements Actions {

	private static final long serialVersionUID = -3779181141505998764L;
	
	private List<UUID> users;

	@Override
	public Response run(String addressIp) {
		
		return null;
	}

	public RefreshFriendsList(List<User> users) {
		this.users = new ArrayList<UUID>();
		users.stream().forEach(u -> this.users.add(u.getUuid()));
	}

}
