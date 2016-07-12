package actions;

import java.util.List;

import database.DataBaseController;
import database.User;

public class SignInSignOut implements Actions {

	private static final long serialVersionUID = -1766433058468964068L;
	
	private String userLogin;
	private boolean direction;

	public Response run() {
		DataBaseController dbController = new DataBaseController();
			User user = dbController.findByPrimaryKey(User.class, userLogin);
			user.setStatus(direction);
			user.setIp(DataHelper.getIp());
			List<User> friends = dbController.executeNamedQuery(User.class, DataBaseController.FIND_FRIENDS, userLogin);
		return new Response(friends);
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
