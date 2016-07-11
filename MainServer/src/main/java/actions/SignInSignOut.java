package actions;

import java.util.List;

import database.DataBaseController;
import database.User;

public class SignInSignOut implements Actions {
	private static final long serialVersionUID = 1L;

	private String userLogin;
	private boolean direction;

	public Response run() {
		DataBaseController dbController = new DataBaseController();
			User user = dbController.findByPrimaryKey(User.class, userLogin);
			user.setStatus(direction);
			user.setIp(DataHelper.getIp());
			List<User> friends = dbController.executeNamedQuery(User.class, Actions.FIND_FRIENDS, userLogin);
		Response response = new Response();
		response.setUsers(friends);
		return response;
	}

	public String getLogin() {
		return userLogin;
	}

	public void setLogin(String login) {
		this.userLogin = login;
	}

	public boolean getDirection() {
		return direction;
	}

	/**
	 * @param direction - true if logIn, false if logOut
	 * */
	public void setDirection(boolean direction) {
		this.direction = direction;
	}
}
