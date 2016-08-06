package database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import actions.DeviceType;


/**
 * The persistent class for the user_current_details database table.
 * 
 */
public class UserCurrentDetail implements Entitys {
	private static final long serialVersionUID = 1L;

	private String sessionId;

	private String detailid;

	private DeviceType device;

	private String ip;

	private List<User> users;

	public UserCurrentDetail() {
	}

	public UserCurrentDetail(UUID randomUUID, DeviceType device, String ip, User user) {
		this.sessionId = randomUUID.toString();
		this.device = device;
		this.ip = ip;
		users = new ArrayList<User>(Arrays.asList(user));
	}

	public UUID getSessionId() {
		return UUID.fromString(this.sessionId);
	}

	public void setSessionId(UUID sessionId) {
		this.sessionId = sessionId.toString();
	}

	public String getDetailid() {
		return this.detailid;
	}

	public void setDetailid(String detailid) {
		this.detailid = detailid;
	}

	public DeviceType getDevice() {
		return this.device;
	}

	public void setDevice(DeviceType device) {
		this.device = device;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public void setUser(User user) {
		List<User> users = new ArrayList<User>();
		users.add(user);
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setUserCurrentDetail(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setUserCurrentDetail(null);

		return user;
	}

	public String getPrimaryKey() {
		return getSessionId().toString();
	}
}