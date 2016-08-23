package database.memorydatabase;

import java.util.UUID;

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

	@Id
	private String uuid;

	private String de;

	private String en;

	private String pl;

	public LanguageEntity(UUID uuid, String de, String en, String pl) {
		super();
		this.de = de;
		this.en = en;
		this.pl = pl;
		this.uuid = uuid.toString();
	}

	public LanguageEntity() {
	}

	public String getDe() {
		return this.de;
	}

	public void setDe(String de) {
		this.de = de;
	}

	public String getEn() {
		return this.en;
	}

	public void setEn(String en) {
		this.en = en;
	}

	public String getPl() {
		return this.pl;
	}

	public void setPl(String pl) {
		this.pl = pl;
	}

	public UUID getUuid() {
		return UUID.fromString(uuid);
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid.toString();
	}

	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return null;
	}

}