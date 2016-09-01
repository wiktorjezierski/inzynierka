package actions;

import java.util.UUID;

public class SignOutAction implements Actions {

	private static final long serialVersionUID = -72617469735184226L;
	
	private String userLogin;
	private UUID sessionId;

	public Response run(String addressIp) {
		return null;
	}
	
	public SignOutAction(String userLogin, UUID sessionId) {
		this.userLogin = userLogin;
		this.sessionId = sessionId;
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
