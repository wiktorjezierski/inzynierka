package database.memorydatabase;


import java.text.SimpleDateFormat;
import java.util.Date;
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
	@NamedQuery(name="History.findHistoryByUser", query="SELECT h FROM HistoryEntity h WHERE h.userBean.uuid=?1 OR h.userBean.uuid=?2 ORDER BY h.date ASC"),
	@NamedQuery(name="History.findFilesByUser", query="SELECT h FROM HistoryEntity h WHERE h.isFile=true AND (h.userBean.uuid=?1 OR h.userBean.uuid=?2) ORDER BY h.date ASC")
})
public class HistoryEntity implements Entitys {
	private static final long serialVersionUID = 1L;

	@Id
	private String uuid;
	
	private String content;

	private Date date;
	
	@Column(name="IS_FILE")
	private boolean isFile;
	
	//bi-directional many-to-one association to User
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="USER", referencedColumnName="UUID")
	private UserEntity userBean;
	
	//bi-directional many-to-one association to User
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="FILE", referencedColumnName="UUID")
	private FileEntity fileEntity;

	public HistoryEntity() {
		uuid = UUID.randomUUID().toString();
		date = new Date();
	}
	
	public HistoryEntity(String content, UserEntity userBean) {
		this.isFile = false;
		this.content = content;
		this.userBean = userBean;
		this.uuid = UUID.randomUUID().toString();
		date = new Date();
	}
	
	public HistoryEntity(String content, Date date, boolean isFile, UserEntity userBean, FileEntity fileEntity) {
		super();
		this.uuid = UUID.randomUUID().toString();
		this.content = content;
		this.date = date;
		this.isFile = isFile;
		this.userBean = userBean;
		this.fileEntity = fileEntity;
	}

	public HistoryEntity(String content, Date date, boolean isFile, UserEntity userBean) {
		this.uuid = UUID.randomUUID().toString();
		this.content = content;
		this.date = date;
		this.isFile = isFile;
		this.userBean = userBean;
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
	
	public Date getDate() {
		return date;
	}
	
	public String getFormatedDate() {
		return new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(date);
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public boolean isFile() {
		return isFile;
	}

	public void setFile(boolean isFile) {
		this.isFile = isFile;
	}

	public FileEntity getFileEntity() {
		return fileEntity;
	}

	public void setFileEntity(FileEntity fileEntity) {
		this.fileEntity = fileEntity;
	}

	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return null;
	}

}