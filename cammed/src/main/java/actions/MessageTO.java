package actions;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class MessageTO implements Serializable {

	private static final long serialVersionUID = 8985162540577327498L;
	
	private String uuid;
	private String content;
	private Date date;
	private boolean isFile;
	private String userFrom;
	private String usetTo;
	
	public MessageTO(String content, boolean isFile, String userFrom, String usetTo) {
		this.uuid = UUID.randomUUID().toString();
		this.content = content;
		this.date = new Date();
		this.isFile = isFile;
		this.userFrom = userFrom;
		this.usetTo = usetTo;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean getIsFile() {
		return isFile;
	}

	public void setIsFile(boolean isFile) {
		this.isFile = isFile;
	}

	public String getUserFrom() {
		return userFrom;
	}

	public void setUserFrom(String userFrom) {
		this.userFrom = userFrom;
	}

	public String getUsetTo() {
		return usetTo;
	}

	public void setUsetTo(String usetTo) {
		this.usetTo = usetTo;
	}
}
