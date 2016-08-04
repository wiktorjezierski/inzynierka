package database;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String login;

	private String imie;

	private String nazwisko;

	private boolean status;

	//bi-directional many-to-one association to UserCurrentDetail
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="DETAILS_ID")
	private UserCurrentDetail userCurrentDetail;

	public User() {
	}
	
	public User(String login, String imie, String nazwisko, boolean status) {
		super();
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

}