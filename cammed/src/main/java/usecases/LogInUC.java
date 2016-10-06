package usecases;

import java.util.List;
import java.util.UUID;

import actions.DeviceType;
import actions.Response;
import actions.SignInAction;
import connections.Client;
import connections.ClientMessages;
import database.Entitys;
import database.memorydatabase.DataBaseController;
import database.memorydatabase.UserEntity;
import masterdata.SystemParameter;

public class LogInUC {
	
	private DataBaseController mController;
	
	public LogInUC() {
		this.mController = new DataBaseController();
	}

	public Response execute(String login, String password) {
		try {
			UserEntity localUser = null;
			Client client = Client.connectWithMainSerwer();

			SignInAction logowanie = new SignInAction(login, password, true, DeviceType.PC);
			client.writeObject(logowanie);
			Response response = (Response) client.readObject();
			client.closeConnection();
			
			if(response.isConfirmation()){
				SystemParameter.put(SystemParameter.MY_LOGIN, login);
				SystemParameter.put(SystemParameter.SESSION_ID, UUID.fromString(response.getValue()));
				
				List<UserEntity> users = mController.executeNamedQuery(UserEntity.class, Entitys.USER_BY_LOGIN, login);
				if(users.isEmpty()) {				// dociagnij z serwera potrzebne dane i zapisz tego usera do bazy
					localUser = new UserEntity(response.getUsers().get(0));
					mController.saveToDataBase(localUser);
				}
				else {
					localUser = users.get(0);
				}
				
				SystemParameter.put(SystemParameter.USER, localUser);
				response.getUsers().remove(0);
				
				runMessages();
			}
			
			return response;
		} catch (Exception e) {
			return new Response(false);
		}
	}

	private void runMessages() {
		Thread clientMessages = new ClientMessages();
		clientMessages.start();
	}
}
