package actions;

import java.io.Serializable;

public class Response implements Serializable {
	
	private static final long serialVersionUID = -8662492570050468270L;
	
	private boolean confirmation;

	public Response(boolean confirmation) {
		this.confirmation = confirmation;
	}

	public boolean isConfirmation() {
		return confirmation;
	}

	public void setConfirmation(boolean confirmation) {
		this.confirmation = confirmation;
	}
}
