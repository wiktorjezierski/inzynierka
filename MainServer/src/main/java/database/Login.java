package database;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the logins database table.
 * 
 */
@Entity
@Table(name="logins")
@NamedQueries({
	@NamedQuery(name="Login.findAll", query="SELECT l FROM Login l"),
	@NamedQuery(name="Login.FindLoginByLogin", query="SELECT l FROM Login l where l.login=?1")
})
public class Login implements Entitys {
	private static final long serialVersionUID = 1L;

	@Id
	private String uuid;
	
	private String login;

	private String password;

	public Login() {
	}
	
	public Login(String login, String password) {
		this.uuid = UUID.randomUUID().toString();
		this.login = login;
		this.password = password;
	}
	
	public UUID getUuid() {
		return UUID.fromString(uuid);
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid.toString();
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPrimaryKey() {
		return uuid;
	}
}