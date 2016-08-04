package actions;

import java.util.List;
import java.util.UUID;

import database.DataBaseController;
import database.Login;
import database.User;
import database.UserCurrentDetail;

public class SignInSignOut implements Actions {

	private static final long serialVersionUID = -1766433058468964068L;

	private String userLogin;
	private String userPassword;
	private boolean direction;
	private DeviceType device;

	public SignInSignOut(String userLogin, String userPassword, boolean direction, DeviceType device) {
		this.userLogin = userLogin;
		this.userPassword = userPassword;
		this.direction = direction;
		this.device = device;
	}

	public Response run() {
		DataBaseController mController = new DataBaseController();
		Login login = mController.findByPrimaryKey(Login.class, userLogin);

		if (userPassword != null && login != null && userPassword.equals(login.getPassword())) {
			User user = mController.findByPrimaryKey(User.class, userLogin);
			user.setStatus(direction);

			UserCurrentDetail details = user.getUserCurrentDetail();
			if (details != null) {
				mController.remove(details);
			}

			UserCurrentDetail userCurrentDetail = new UserCurrentDetail(UUID.randomUUID(), device, "ip", user);
			user.setUserCurrentDetail(userCurrentDetail);
			mController.saveToDataBase(user);

			List<User> friends = mController.executeNamedQuery(User.class, DataBaseController.FIND_FRIENDS, userLogin);
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
	 * @param direction
	 *            - true if logIn, false if logOut
	 */
	public void setDirection(boolean direction) {
		this.direction = direction;
	}
}
