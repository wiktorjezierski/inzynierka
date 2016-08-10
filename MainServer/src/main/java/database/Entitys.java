package database;

import java.io.Serializable;

public interface Entitys extends Serializable {
	
	public final static String FIND_FRIENDS = "Relation.findFriends";
	public final static String LOG_IN = "User.UpdateStatusLogIn";
	public final static String LOG_OUT = "User.UpdateStatusLogOut";
	public final static String FIND_USER_BY_LOGIN = "User.FindUserByLogin";
	public final static String FIND_LOGIN_BY_LOGIN = "Login.FindLoginByLogin";

	public String getPrimaryKey();
}
