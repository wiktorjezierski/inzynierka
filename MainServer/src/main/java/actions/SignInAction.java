package actions;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import database.DataBaseController;
import database.Login;
import database.Relation;
import database.User;
import database.UserCurrentDetail;

public class SignInAction implements Actions {

	private static final long serialVersionUID = -1766433058468964068L;

	private String userLogin;
	private String userPassword;
	private boolean direction;
	private DeviceType device;

	public SignInAction(String userLogin, String userPassword, boolean direction, DeviceType device) {
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

			List<Relation> relations = mController.executeNamedQuery(Relation.class, DataBaseController.FIND_FRIENDS, userLogin);
			List<User> friends = findActivFriends(relations);
			
			return new Response(friends);
		} else {
			return new Response(false);
		}
	}

	private List<User> findActivFriends(List<Relation> relations) {
		List<User> friends = new ArrayList<User>();
		for (Relation relation : relations) {
			User user1 = relation.getUser1();
			if(!user1.getLogin().equals(userLogin) && user1.getUserCurrentDetail() != null){
				friends.add(user1);
			} else if(relation.getUser2().getUserCurrentDetail() != null){
				friends.add(relation.getUser2());
			}
		}
		return friends;
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
