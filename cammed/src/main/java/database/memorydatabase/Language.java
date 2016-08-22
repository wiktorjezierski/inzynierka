package database.memorydatabase;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the LANGUAGE database table.
 * 
 */
@Entity
@NamedQuery(name="Language.findAll", query="SELECT l FROM Language l")
public class Language implements Serializable {
	private static final long serialVersionUID = 1L;

	private Object de;

	private Object en;

	private Object pl;

	@Id
	private Object uuid;

	public Language() {
	}

	public Object getDe() {
		return this.de;
	}

	public void setDe(Object de) {
		this.de = de;
	}

	public Object getEn() {
		return this.en;
	}

	public void setEn(Object en) {
		this.en = en;
	}

	public Object getPl() {
		return this.pl;
	}

	public void setPl(Object pl) {
		this.pl = pl;
	}

	public Object getUuid() {
		return this.uuid;
	}

	public void setUuid(Object uuid) {
		this.uuid = uuid;
	}

}