package database;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the logins database table.
 * 
 */
@Entity
@Table(name="logins")
@NamedQuery(name="Login.findAll", query="SELECT l FROM Login l")
public class Login implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String login;

	private String password;

	public Login() {
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
}