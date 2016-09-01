package actions;

import java.util.UUID;

public class CallAction implements Actions {
	
	private static final long serialVersionUID = -125696276215436557L;
	private UUID userUUID;

	@Override
	public Response run(String addressIp) {
		return null;
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
