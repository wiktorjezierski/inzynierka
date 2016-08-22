package database.memorydatabase;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the USERS database table.
 * 
 */
@Entity
@Table(name="USERS")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String uuid;

	private Object imie;

	private Object login;

	private Object nazwisko;

	private Object status;

	//bi-directional many-to-one association to History
	@OneToMany(mappedBy="userBean")
	private List<History> histories;

	public User() {
	}

	public Object getImie() {
		return this.imie;
	}

	public void setImie(Object imie) {
		this.imie = imie;
	}

	public Object getLogin() {
		return this.login;
	}

	public void setLogin(Object login) {
		this.login = login;
	}

	public Object getNazwisko() {
		return this.nazwisko;
	}

	public void setNazwisko(Object nazwisko) {
		this.nazwisko = nazwisko;
	}

	public Object getStatus() {
		return this.status;
	}

	public void setStatus(Object status) {
		this.status = status;
	}

	public List<History> getHistories() {
		return this.histories;
	}

	public void setHistories(List<History> histories) {
		this.histories = histories;
	}

	public History addHistory(History history) {
		getHistories().add(history);
		history.setUserBean(this);

		return history;
	}

	public History removeHistory(History history) {
		getHistories().remove(history);
		history.setUserBean(null);

		return history;
	}

}