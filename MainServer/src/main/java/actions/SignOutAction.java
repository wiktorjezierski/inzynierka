package actions;

import java.util.UUID;

import database.DataBaseController;
import database.Entitys;
import database.User;
import database.UserCurrentDetail;

public class SignOutAction implements Actions {

	private static final long serialVersionUID = -72617469735184226L;
	
	private String userLogin;
	private UUID sessionId;

	public Response run(String addressIp) {
		DataBaseController mController = new DataBaseController();
		try {
			while(!mController.transactionIsActive());
			mController.beginTransaction();
			User user = mController.executeNamedQuery(User.class, Entitys.FIND_USER_BY_LOGIN, userLogin).get(0);
			UserCurrentDetail userCurrentDetail = user.getUserCurrentDetail();
			if (sessionId.equals(userCurrentDetail.getSessionId())) {
				mController.remove(userCurrentDetail);
				user.setUserCurrentDetail(null);
				user.setStatus(false);
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
