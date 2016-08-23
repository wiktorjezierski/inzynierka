package database;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQueries({
	@NamedQuery(name="User.findAll", query="SELECT u FROM User u"),
	@NamedQuery(name="User.FindUserByLogin", query="SELECT u FROM User u where u.login=?1")
})
public class User implements Entitys {
	private static final long serialVersionUID = 1L;

	@Id
	private String uuid;
	
	private String login;

	private String imie;

	private String nazwisko;

	private boolean status;
	
	private int amountFriends;

	//bi-directional many-to-one association to UserCurrentDetail
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="OR_USER_DETAIL")
	private UserCurrentDetail userCurrentDetail;

	public User() {
	}
	
	public User(String login, String imie, String nazwisko, boolean status) {
		super();
		this.uuid = UUID.randomUUID().toString();
		this.login = login;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.status = status;
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

	public int getAmountFriends() {
		return amountFriends;
	}

	public void setAmountFriends(int amountFriends) {
		this.amountFriends = amountFriends;
	}

	public String getPrimaryKey() {
		return getLogin();
	}
}