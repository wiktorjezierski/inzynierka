package database;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the message database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Message.findAll", query="SELECT m FROM Message m"),
	@NamedQuery(name="Message.findByDestinationUser", query="SELECT m FROM Message m WHERE m.usetTo=?1")
})
public class Message implements Entitys {
	private static final long serialVersionUID = 1L;

	@Id
	private String uuid;

	private String content;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@Column(name="is_file")
	private boolean isFile;

	@Column(name="user_from")
	private String userFrom;

	@Column(name="uset_to")
	private String usetTo;

	public Message() {
		date = new Date();
	}

	public Message(String uuid, String content, boolean isFile, String userFrom, String usetTo) {
		super();
		this.uuid = uuid;
		this.content = content;
		this.isFile = isFile;
		this.userFrom = userFrom;
		this.usetTo = usetTo;
		date = new Date();
	}
	
	public Message(String uuid, String content, boolean isFile, String userFrom, String usetTo, Date date) {
		super();
		this.uuid = uuid;
		this.content = content;
		this.isFile = isFile;
		this.userFrom = userFrom;
		this.usetTo = usetTo;
		this.date = date;
	}


	public UUID getUuid() {
		return UUID.fromString(uuid);
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid.toString();
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean getIsFile() {
		return this.isFile;
	}

	public void setIsFile(boolean isFile) {
		this.isFile = isFile;
	}

	public String getUserFrom() {
		return this.userFrom;
	}

	public void setUserFrom(String userFrom) {
		this.userFrom = userFrom;
	}

	public String getUsetTo() {
		return this.usetTo;
	}

	public void setUsetTo(String usetTo) {
		this.usetTo = usetTo;
	}

	@Override
	public String getPrimaryKey() {
		return getUuid().toString();
	}

}