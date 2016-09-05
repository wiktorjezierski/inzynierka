package masterdata;

import java.util.HashMap;
import java.util.Map;

public class SystemParameter {
	
	public final static String SESSION_ID = "sessionID";
	public final static String MY_LOGIN = "userLogin";
	public final static String USER = "currentUser";
//	public final static String SESSION_ID = "sessionID";
//	public final static String SESSION_ID = "sessionID";
//	public final static String SESSION_ID = "sessionID";
	
	private static Map<String, Object> parameters = new HashMap<String, Object>();
	
	public static void put(String key, Object value) {
		parameters.put(key, value);
	}
	
	public static Object get(String key) {
		return parameters.get(key);
	}

}
