package actions;

import java.io.Serializable;

public interface Actions extends Serializable {

	/**
	 * default function for all actions in server application
	 */
	public Response run();
}
