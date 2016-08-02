package actions;

import java.util.List;

import database.DataBaseController;
import database.Login;
import database.User;

public class SignInSignOut implements Actions {

	private static final long serialVersionUID = -1766433058468964068L;
	
	private String userLogin;
	private String userPassword;
	private boolean direction;
	
	public SignInSignOut(String userLogin, String userPassword, boolean direction) {
		this.userLogin = userLogin;
		this.userPassword = userPassword;
		this.direction = direction;
	}

	public Response run() {
		DataBaseController dbController = new DataBaseController();
		Login login = dbController.findByPrimaryKey(Login.class, userLogin);
		if (userPassword != null && login != null && userPassword.equals(login.getPassword())) {
			User user = dbController.findByPrimaryKey(User.class, userLogin);
			user.setStatus(direction);
			user.setIp(DataHelper.getIp());
			List<User> friends = dbController.executeNamedQuery(User.class, DataBaseController.FIND_FRIENDS, userLogin);
			return new Response(friends);
		} else {
			return new Response(false);
		}
	}

	public String getLogin() {
		return userLogin;
	}

	public void setLogin(String login) {
		this.userLogin = login;
	}
	
	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
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
