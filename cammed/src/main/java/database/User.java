package database;

import java.util.UUID;

/**
 * The persistent class for the users database table.
 * 
 */
public class User implements Entitys {
	private static final long serialVersionUID = 1L;
	
	private String uuid;
	private String login;
	private String imie;
	private String nazwisko;
	private boolean status;

	private UserCurrentDetail userCurrentDetail;

	public User() {
	}
	
	public User(String uuid, String login, String imie, String nazwisko, boolean status) {
		super();
		this.uuid = uuid;
		this.login = login;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.status = status;
	}

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

	public boolean getStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public UserCurrentDetail getUserCurrentDetail() {
		return this.userCurrentDetail;
	}

	public void setUserCurrentDetail(UserCurrentDetail userCurrentDetail) {
		this.userCurrentDetail = userCurrentDetail;
	}
	
	public UUID getUuid() {
		return UUID.fromString(uuid);
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid.toString();
	}

	public String getPrimaryKey() {
		return getLogin();
	}
}