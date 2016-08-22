package database.memorydatabase;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import database.Entitys;


/**
 * The persistent class for the LANGUAGE database table.
 * 
 */
@Entity
@Table(name="LANGUAGE")
@NamedQuery(name="Language.findAll", query="SELECT l FROM LanguageEntity l")
public class LanguageEntity implements Entitys {
	private static final long serialVersionUID = 1L;

	private Object de;

	private Object en;

	private Object pl;

	@Id
	private Object uuid;

	public LanguageEntity() {
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

	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return null;
	}

}