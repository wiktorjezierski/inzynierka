package database;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the logins database table.
 * 
 */
@Entity
@Table(name="logins")
@NamedQuery(name="Login.findAll", query="SELECT l FROM Login l")
public class Login implements Entitys {
	private static final long serialVersionUID = 1L;

	@Id
	private String login;

	private String password;

	public Login() {
	}
	
	public Login(String login, String password) {
		this.login = login;
		this.password = password;
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
		return getLogin();
	}
}