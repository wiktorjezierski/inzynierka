package actions;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import database.DataBaseController;
import database.Entitys;
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

	public Response run(String addressIp) {
		DataBaseController mController = new DataBaseController();
		try {
			mController.beginTransaction();
			Login login = mController.executeNamedQuery(Login.class, Entitys.FIND_LOGIN_BY_LOGIN, userLogin).get(0);

			if (userPassword != null && login != null && userPassword.equals(login.getPassword())) {
				User user = mController.executeNamedQuery(User.class, Entitys.FIND_USER_BY_LOGIN, userLogin).get(0);
				user.setStatus(direction);

				UserCurrentDetail details = user.getUserCurrentDetail();
				if (details != null) {
					mController.remove(details);
				}

				UserCurrentDetail userCurrentDetail = new UserCurrentDetail(UUID.randomUUID(), device, addressIp, user);
				user.setUserCurrentDetail(userCurrentDetail);
				mController.saveToDataBase(user);

				List<Relation> relations = mController.executeNamedQuery(Relation.class, Entitys.FIND_FRIENDS,userLogin);
				List<User> friends = findActivFriends(relations);
				friends.stream().forEach( f -> f.setUserCurrentDetail(null));
				mController.commitTransaction();
				
				return new Response(friends);
			}
		} catch (RuntimeException e) {
			mController.rollbackTransaction();
		}
		return new Response(false);
	}

	private List<User> findActivFriends(List<Relation> relations) {
		List<User> friends = new ArrayList<User>();
		for (Relation relation : relations) {
			User user1 = relation.getUser1();
			if(!user1.getLogin().equals(userLogin)){
				friends.add(user1);
			} else {
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
