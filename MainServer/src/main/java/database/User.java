package database;

import java.io.Serializable;
import javax.persistence.*;

import actions.DeviceType;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQueries({
	@NamedQuery(name="User.findAll", query="SELECT u FROM User u"),
	@NamedQuery(name="User.UpdateStatusLogIn", query="UPDATE User u SET u.status=true WHERE u.login=?2"),
	@NamedQuery(name="User.UpdateStatusLogOut", query="UPDATE User u SET u.status=false WHERE u.login=?2"),
	@NamedQuery(name="User.CheckLogin", query="SELECT u FROM User u WHERE u.login=?1")
})
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String login;

	private String imie;

	private String nazwisko;
	
	private boolean status;
	
	@Column(name="IP")
	private String ip;

	@Column(name="DEVICE")
	@Enumerated(EnumType.ORDINAL)
	private DeviceType deviceType;
	
	public User() {
	}
	
	public User(String login, String imie, String nazwisko, boolean status, String ip, DeviceType deviceType) {
		super();
		this.login = login;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.status = status;
		this.ip = ip;
		this.deviceType = deviceType;
	}
	
	public User(String login, String imie, String nazwisko, boolean status) {
		super();
		this.login = login;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.status = status;
	}

	//bi-directional one-to-one association to Relation
	@OneToOne(mappedBy="user1")
	private Relation relation1;

	//bi-directional one-to-one association to Relation
	@OneToOne(mappedBy="user2", fetch=FetchType.LAZY)
	private Relation relation2;

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getImie() {
		return this.imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNazwisko() {
		return this.nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public Relation getRelation1() {
		return this.relation1;
	}

	public void setRelation1(Relation relation1) {
		this.relation1 = relation1;
	}

	public Relation getRelation2() {
		return this.relation2;
	}

	public void setRelation2(Relation relation2) {
		this.relation2 = relation2;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public DeviceType getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}
}