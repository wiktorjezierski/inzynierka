package actions;

import database.DataBaseController;
import database.User;

public class SignUP implements Actions {
	
	private static final long serialVersionUID = 3307103721179068946L;

	private String login;

	private String imie;

	private String nazwisko;
	
	private boolean status;
	
	private String ip;
	
	public SignUP(String login, String imie, String nazwisko, boolean status) {
		super();
		this.login = login;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.status = status;
	}

	public Response run() {
		try {
			
			User user = new User(login, imie, nazwisko, status, DataHelper.getIp());
			DataBaseController dbController = new DataBaseController();
			dbController.saveToDataBase(user);
			return new Response(true);

		} catch (Exception e) {
			e.printStackTrace();
			return new Response(false);
		}
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
}
