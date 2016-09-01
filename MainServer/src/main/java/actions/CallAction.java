package actions;

import java.util.UUID;

import database.DataBaseController;
import database.User;
import database.UserCurrentDetail;

public class CallAction implements Actions {
	
	private static final long serialVersionUID = -125696276215436557L;
	private UUID userUUID;

	@Override
	public Response run(String addressIp) {
		DataBaseController mController = new DataBaseController();
		
		mController.beginTransaction();
		User user = mController.findByPrimaryKey(User.class, userUUID.toString());
		UserCurrentDetail userCurrentDetail = user.getUserCurrentDetail();
		mController.commitTransaction();
		
		return new Response(userCurrentDetail.getIp(), userCurrentDetail.getDevice());
	}
	
	public CallAction(UUID userUUID) {
		this.userUUID = userUUID;
	}

	public UUID getUserUUID() {
		return userUUID;
	}

	public void setUserUUID(UUID userUUID) {
		this.userUUID = userUUID;
	}
}
