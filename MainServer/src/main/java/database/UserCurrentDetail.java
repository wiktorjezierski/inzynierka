package database;

import java.io.Serializable;
import javax.persistence.*;

import actions.DeviceType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * The persistent class for the user_current_details database table.
 * 
 */
@Entity
@Table(name="user_current_details")
@NamedQuery(name="UserCurrentDetail.findAll", query="SELECT u FROM UserCurrentDetail u")
public class UserCurrentDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SESSION_ID")
	private String sessionId;

	private String detailid;

	private byte device;

	private String ip;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="userCurrentDetail")
	private List<User> users;

	public UserCurrentDetail() {
	}

	public UserCurrentDetail(UUID randomUUID, DeviceType device, String ip) {
		this.sessionId = randomUUID.toString();
		this.device = convertDeviceType(device);;
		this.ip = ip;
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

	public byte getDevice() {
		return this.device;
	}

	public void setDevice(DeviceType device) {
		this.device = convertDeviceType(device);
	}

	private byte convertDeviceType(DeviceType device) {
		if(device == DeviceType.PC)
			return 0;
		else
			return 1;
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

}