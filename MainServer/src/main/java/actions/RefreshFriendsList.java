package actions;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import database.DataBaseController;
import database.User;

public class RefreshFriendsList implements Actions {

	private static final long serialVersionUID = -3779181141505998764L;
	
	private List<UUID> users;

	@Override
	public Response run(String addressIp) {
		String sql = "SELECT u FROM User u WHERE ";
		
		for (int i = 0; i < users.size(); i++) {
			if(i == 0) {
				sql += "u.uuid='" + users.get(i) + "'";
				continue;
			} 
			
			sql += " OR u.uuid='" + users.get(i) + "'";
		}
		
		DataBaseController mController = new DataBaseController();
		mController.openConnection();
		
		mController.beginTransaction();
		List<User> result = (List<User>) mController.executeQuery(sql);
		mController.rollbackTransaction();
		
		return new Response(result);
	}

	public RefreshFriendsList(List<User> users) {
		this.users = new ArrayList<UUID>();
		users.stream().forEach(u -> this.users.add(u.getUuid()));
	}

}
