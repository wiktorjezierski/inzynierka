package database;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;



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
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private UserCurrentDetail detailsID;
	
	public User() {
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

	public UserCurrentDetail getDetailsID() {
		return detailsID;
	}

	public void setDetailsID(UserCurrentDetail detailsID) {
		this.detailsID = detailsID;
	}
}