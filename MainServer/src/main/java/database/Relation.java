package database;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the relations database table.
 * 
 */
@Entity
@Table(name="relations")
@NamedQueries({
	@NamedQuery(name="Relation.findAll", query="SELECT r FROM Relation r"),
	@NamedQuery(name="Relation.findFriends", query="SELECT r FROM Relation r JOIN r.user1 u1 JOIN r.user2 u2 WHERE u1.login=?1 or u2.login=?1")	// zweryfikowac to
})
public class Relation implements Entitys {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	private Date date;

	@Id
	private int id;

	//bi-directional one-to-one association to User
	@OneToOne
	@JoinColumn(name="LOGIN1")
	private User user1;

	//bi-directional one-to-one association to User
	@OneToOne
	@JoinColumn(name="LOGIN2")
	private User user2;

	public Relation() {
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser1() {
		return this.user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return this.user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

	public String getPrimaryKey() {
		return Integer.toString(getId());
	}
}