package database;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import actions.DeviceType;


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
	private UUID sessionId;

	@Column(name="DEVICE")
	@Enumerated(EnumType.ORDINAL)
	private DeviceType device;

	private String ip;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "detailsID")
	private User detailid;

	public UserCurrentDetail() {
	}
	
	public UserCurrentDetail(UUID sessionId, DeviceType device, String ip) {
		this.sessionId = sessionId;
		this.device = device;
		this.ip = ip;
	}

	public UUID getSessionId() {
		return this.sessionId;
	}

	public void setSessionId(UUID sessionId) {
		this.sessionId = sessionId;
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

	public User getDetailid() {
		return this.detailid;
	}

	public void setdetailid(User login) {
		this.detailid = login;
	}
}