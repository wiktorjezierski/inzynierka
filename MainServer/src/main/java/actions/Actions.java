package actions;

import java.io.Serializable;

public interface Actions extends Serializable {

	public final static String FIND_FRIENDS = "Relation.findFriends";
	public final static String LOG_IN = "User.UpdateStatusLogIn";
	public final static String LOG_OUT = "User.UpdateStatusLogOut";
	public final static String CHECK_LOGIN_FOR_FREE = "User.CheckLogin";
	public final static String MESSAGE_FOR_USER = "Message.findByDestinationUser";

	/**
	 * default function for all actions in server application
	 */
	public Response run(String addressIp);
}
