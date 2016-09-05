package database;

import java.io.Serializable;

public interface Entitys extends Serializable {
	
	public final static String FIND_FRIENDS = "Relation.findFriends";
	public final static String LOG_IN = "User.UpdateStatusLogIn";
	public final static String LOG_OUT = "User.UpdateStatusLogOut";
	
	
	public final static String HISTORY = "History.findHistoryByUser";
	public final static String USER_BY_LOGIN = "User.FindUserByLogin";

	public String getPrimaryKey();
}
