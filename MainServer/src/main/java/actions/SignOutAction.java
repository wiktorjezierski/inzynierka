package actions;

import java.util.UUID;

import database.DataBaseController;
import database.User;
import database.UserCurrentDetail;

public class SignOutAction implements Actions {

	private static final long serialVersionUID = -72617469735184226L;
	
	private String userLogin;
	private UUID sessionId;

	public Response run() {
		DataBaseController mController = new DataBaseController();
		try {
			mController.beginTransaction();
			User user = mController.findByPrimaryKey(User.class, userLogin);
			UserCurrentDetail userCurrentDetail = user.getUserCurrentDetail();
			if (userCurrentDetail.getSessionId() == sessionId) {
				mController.remove(userCurrentDetail);
				mController.commitTransaction();
				return new Response(true);
			}
		} catch (Exception e) {
			mController.rollbackTransaction();
		}
		mController.commitTransaction();
		return new Response(false);
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public UUID getSessionId() {
		return sessionId;
	}

	public void setSessionId(UUID sessionId) {
		this.sessionId = sessionId;
	}
}
