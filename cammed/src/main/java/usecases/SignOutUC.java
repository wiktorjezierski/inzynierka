package usecases;

import java.util.UUID;

import actions.Actions;
import actions.SignOutAction;
import connections.Client;
import masterdata.SystemParameter;

public class SignOutUC implements UseCase {

	@Override
	public void execute() throws Exception {
		Actions signOut = new SignOutAction((String)SystemParameter.get(SystemParameter.MY_LOGIN), (UUID)SystemParameter.get(SystemParameter.SESSION_ID));
		
		Client client = Client.connectWithMainSerwer();
		client.writeObject(signOut);
	}

}
