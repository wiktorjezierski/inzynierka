package usecases;

import actions.DeviceType;
import actions.Response;
import actions.SignInAction;
import connections.Client;

public class LogInUC {

	public Response execute(String login, String password) {
		try {
			Client client = Client.connectWithMainSerwer();

			SignInAction logowanie = new SignInAction(login, password, true, DeviceType.PC);
			client.writeObject(logowanie);
			Response response = (Response) client.readObject();
			client.closeConnection();
			return response;
		} catch (Exception e) {
			return new Response(false);
		}
	}
}
