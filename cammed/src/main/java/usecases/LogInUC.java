package usecases;

import java.util.UUID;

import actions.DeviceType;
import actions.Response;
import actions.SignInAction;
import connections.Client;
import masterdata.SystemParameter;

public class LogInUC {

	public Response execute(String login, String password) {
		try {
			Client client = Client.connectWithMainSerwer();

			SignInAction logowanie = new SignInAction(login, password, true, DeviceType.PC);
			client.writeObject(logowanie);
			Response response = (Response) client.readObject();
			client.closeConnection();
			
			SystemParameter.put(SystemParameter.MY_LOGIN, login);	// tutaj jest blad login = wjeziorko
			SystemParameter.put(SystemParameter.SESSION_ID, UUID.fromString(response.getValue()));
			
			return response;
		} catch (Exception e) {
			return new Response(false);
		}
	}
}
