package actions;

import database.DataBaseController;
import database.Login;
import database.User;

public class SignUPAction implements Actions {

	private static final long serialVersionUID = 3307103721179068946L;

	private String login;

	private String password;

	private String imie;

	private String nazwisko;

	private boolean status;

	public SignUPAction(String login, String password, String imie, String nazwisko, boolean status) {
		super();
		this.login = login;
		this.password = password;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.status = status;
	}

	public Response run(String addressIp) {
		DataBaseController mController = new DataBaseController();
		try {
			mController.beginTransaction();
			
			User user = new User(login, imie, nazwisko, status);
			mController.saveToDataBase(user);
			
			Login login = new Login(this.login, password);
			mController.saveToDataBase(login);
			
			mController.commitTransaction();
			return new Response(true);

		} catch (Exception e) {
			e.printStackTrace();
			mController.rollbackTransaction();
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
