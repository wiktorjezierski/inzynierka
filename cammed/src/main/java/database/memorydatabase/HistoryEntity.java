package database.memorydatabase;


import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import database.Entitys;


/**
 * The persistent class for the HISTORY database table.
 * 
 */
@Entity
@Table(name="HISTORY")
@NamedQueries({
	@NamedQuery(name="History.findAll", query="SELECT h FROM HistoryEntity h"),
	@NamedQuery(name="History.findHistoryByUser", query="SELECT h FROM HistoryEntity h WHERE h.userBean.uuid=?1 OR h.userBean.uuid=?2 ORDER BY h.date ASC")
})
public class HistoryEntity implements Entitys {
	private static final long serialVersionUID = 1L;

	@Id
	private String uuid;
	
	private String content;

	private LocalDateTime date;
	
	@Column(name="IS_FILE")
	private boolean isFile;
	
	//bi-directional many-to-one association to User
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="USER", referencedColumnName="UUID")
	private UserEntity userBean;
	
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private FileEntity fileEntity;

	public HistoryEntity() {
		uuid = UUID.randomUUID().toString();
		date = LocalDateTime.now();
	}
	
	public HistoryEntity(String uuid, String content, UserEntity userBean) {
		this.uuid = uuid;
		this.content = content;
		this.userBean = userBean;
		date = LocalDateTime.now();
	}
	
	public HistoryEntity(String content, UserEntity userBean) {
		this.uuid = UUID.randomUUID().toString();
		this.content = content;
		this.userBean = userBean;
		date = LocalDateTime.now();
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public UserEntity getUserBean() {
		return this.userBean;
	}

	public void setUserBean(UserEntity userBean) {
		this.userBean = userBean;
	}
	
	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	public boolean isFile() {
		return isFile;
	}

	public void setFile(boolean isFile) {
		this.isFile = isFile;
	}

	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return null;
	}

}