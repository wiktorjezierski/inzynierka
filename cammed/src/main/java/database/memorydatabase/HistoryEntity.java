package database.memorydatabase;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import database.Entitys;


/**
 * The persistent class for the HISTORY database table.
 * 
 */
@Entity
@Table(name="HISTORY")
@NamedQuery(name="History.findAll", query="SELECT h FROM HistoryEntity h")
public class HistoryEntity implements Entitys {
	private static final long serialVersionUID = 1L;

	private Object content;

	@Id
	private Object uuid;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USER", referencedColumnName="UUID")
	private UserEntity userBean;

	public HistoryEntity() {
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

	public UserEntity getUserBean() {
		return this.userBean;
	}

	public void setUserBean(UserEntity userBean) {
		this.userBean = userBean;
	}

	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return null;
	}

}