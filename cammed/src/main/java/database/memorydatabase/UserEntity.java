package database.memorydatabase;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import database.Entitys;


/**
 * The persistent class for the USERS database table.
 * 
 */
@Entity
@Table(name="USERS")
@NamedQuery(name="User.findAll", query="SELECT u FROM UserEntity u")
public class UserEntity implements Entitys {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String uuid;

	private Object imie;

	private Object login;

	private Object nazwisko;

	private Object status;

	//bi-directional many-to-one association to History
	@OneToMany(mappedBy="userBean")
	private List<HistoryEntity> histories;

	public UserEntity() {
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

	public List<HistoryEntity> getHistories() {
		return this.histories;
	}

	public void setHistories(List<HistoryEntity> histories) {
		this.histories = histories;
	}

	public HistoryEntity addHistory(HistoryEntity history) {
		getHistories().add(history);
		history.setUserBean(this);

		return history;
	}

	public HistoryEntity removeHistory(HistoryEntity history) {
		getHistories().remove(history);
		history.setUserBean(null);

		return history;
	}

	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return null;
	}

}