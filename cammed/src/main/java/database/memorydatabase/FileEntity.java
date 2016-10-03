package database.memorydatabase;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import database.Entitys;


/**
 * The persistent class for the files database table.
 * 
 */
@Entity
@Table(name="files")
@NamedQuery(name="File.findAll", query="SELECT f FROM FileEntity f")
public class FileEntity implements Entitys {
	private static final long serialVersionUID = 1L;

	@Id
	private String uuid;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@Column(name="LOCAL_PATH")
	private String localPath;

	private String name;
	
	//bi-directional many-to-one association to History
	@OneToMany(mappedBy="fileEntity", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private List<HistoryEntity> historyEntitys;

	public FileEntity(String name) {
		uuid = UUID.randomUUID().toString();
		date = new Date();
		this.name = name;
	}

	public FileEntity(String uuid, String name) {
		this.uuid = uuid;
		this.date = new Date();
		this.name = name;
	}
	
	public FileEntity() {
	}

	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getLocalPath() {
		return this.localPath;
	}

	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<HistoryEntity> getHistoryEntitys() {
		return historyEntitys;
	}

	public void setHistoryEntitys(List<HistoryEntity> historyEntitys) {
		this.historyEntitys = historyEntitys;
	}

	@Override
	public String getPrimaryKey() {
		return null;
	}

}