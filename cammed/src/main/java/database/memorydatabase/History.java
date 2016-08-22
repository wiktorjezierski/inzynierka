package database.memorydatabase;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the HISTORY database table.
 * 
 */
@Entity
@NamedQuery(name="History.findAll", query="SELECT h FROM History h")
public class History implements Serializable {
	private static final long serialVersionUID = 1L;

	private Object content;

	@Id
	private Object uuid;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USER", referencedColumnName="UUID")
	private User userBean;

	public History() {
	}

	public Object getContent() {
		return this.content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

	public Object getUuid() {
		return this.uuid;
	}

	public void setUuid(Object uuid) {
		this.uuid = uuid;
	}

	public User getUserBean() {
		return this.userBean;
	}

	public void setUserBean(User userBean) {
		this.userBean = userBean;
	}

}