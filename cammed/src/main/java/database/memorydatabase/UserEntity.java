package database.memorydatabase;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
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
@NamedQueries({
	@NamedQuery(name="User.findAll", query="SELECT u FROM UserEntity u"),
	@NamedQuery(name="User.FindUserByLogin", query="SELECT u FROM UserEntity u where u.login=?1")
})
public class UserEntity implements Entitys {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String uuid;

	private String imie;

	private String login;

	private String nazwisko;

	private boolean status;

	//bi-directional many-to-one association to History
	@OneToMany(mappedBy="userBean", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private List<HistoryEntity> histories;

	public UserEntity() {
		uuid = UUID.randomUUID().toString();
	}

	public String getImie() {
		return this.imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
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