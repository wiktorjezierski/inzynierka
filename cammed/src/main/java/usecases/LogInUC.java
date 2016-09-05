package usecases;

import java.util.UUID;

import actions.DeviceType;
import actions.Response;
import actions.SignInAction;
import connections.Client;
import database.Entitys;
import database.memorydatabase.DataBaseController;
import database.memorydatabase.UserEntity;
import masterdata.SystemParameter;

public class LogInUC {

	public Response execute(String login, String password) {
		try {
			Client client = Client.connectWithMainSerwer();

			SignInAction logowanie = new SignInAction(login, password, true, DeviceType.PC);
			client.writeObject(logowanie);
			Response response = (Response) client.readObject();
			client.closeConnection();
			
			DataBaseController mController = new DataBaseController();
			UserEntity user = mController.executeNamedQuery(UserEntity.class, Entitys.USER_BY_LOGIN, login).get(0);
			
			SystemParameter.put(SystemParameter.USER, user);
			SystemParameter.put(SystemParameter.MY_LOGIN, login);
			SystemParameter.put(SystemParameter.SESSION_ID, UUID.fromString(response.getValue()));
			
			return response;
		} catch (Exception e) {
			return new Response(false);
		}
	}
}
