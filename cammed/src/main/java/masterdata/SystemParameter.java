package masterdata;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SystemParameter {
	
	public final static String SESSION_ID = "sessionID";
	public final static String MY_LOGIN = "userLogin";
	public final static String USER = "currentUser";
//	public final static String SESSION_ID = "sessionID";
//	public final static String SESSION_ID = "sessionID";
//	public final static String SESSION_ID = "sessionID";
	
	private static Map<String, Object> parameters = new ConcurrentHashMap<String, Object>();
	
	public synchronized static void put(String key, Object value) {
		parameters.put(key, value);
	}
	
	public synchronized static Object get(String key) {
		return parameters.get(key);
	}

}
