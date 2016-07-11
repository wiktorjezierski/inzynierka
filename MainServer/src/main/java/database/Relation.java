package database;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the relations database table.
 * 
 */
@Entity
@Table(name="relations")
@NamedQueries({
	@NamedQuery(name="Relation.findAll", query="SELECT r FROM Relation r"),
	@NamedQuery(name="Relation.findFriends", query="SELECT u FROM User u JOIN u.relation1 r WHERE r=?1")	// zweryfikowac to
})
public class Relation implements Serializable {
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
}