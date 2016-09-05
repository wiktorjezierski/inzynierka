package gui.helper;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import actions.CallAction;
import actions.Response;
import connections.Client;
import database.Entitys;
import database.User;
import database.memorydatabase.DataBaseController;
import database.memorydatabase.HistoryEntity;
import database.memorydatabase.UserEntity;
import gui.UserHistoryPanel;
import masterdata.SystemParameter;
import usecases.EstablishConnectionUC;
import usecases.SignOutUC;
import usecases.UseCase;

public class Controller {
	
	private DataBaseController mController;
	
	public Controller() {
		mController = new DataBaseController();
	}

	public void friendEstablishConnection(User user) throws IOException, ClassNotFoundException, Exception {
		CallAction callAction = new CallAction(user.getUuid());

		Client client = Client.connectWithMainSerwer();
		client.writeObject(callAction);
		Response response = client.readObject();
		
		UseCase establishConnection = new EstablishConnectionUC(response.getValue(), response.getDeviceType());
		establishConnection.execute();
	}

	public void sortByActiv(List<User> users) {
		int i, j;
		for (i = 0; i < users.size(); i++) {
			if(!users.get(i).getStatus()){
				break;
			}
		}
		
		for (j = users.size() - 1; j >= 0; j--) {
			if(users.get(j).getStatus()){
				break;
			}
		}
		
		if(i < j) {
			User temp = users.get(i);
			users.set(i, users.get(j));
			users.set(j, temp);
			sortByActiv(users);
		} else {
			return;
		}
	}
	
	public static String clearText(String text) {
		String pattern = "(.*)('>)(.*)(</font>)(.*)";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(text);
		
		if(m.matches()) {
			String group = m.group(3);
			return group;
		} else {
			return text;
		}
	}
	
	public static void signOut() throws Exception {
		UseCase signOut = new SignOutUC();
		signOut.execute();
	}
	
	public void persistMessage(String enteredText, UserHistoryPanel userHistory) {
		UserEntity userEntity = (UserEntity) SystemParameter.get(SystemParameter.USER);
		HistoryEntity history = new HistoryEntity(enteredText, userEntity);
		userHistory.addElement(history);
		
		mController.beginTransaction();
		mController.saveToDataBase(history);
		mController.commitTransaction();
	}
	
	public List<HistoryEntity> generateHistoryDetails(User user) {
		DataBaseController mController = new DataBaseController();
		mController.beginTransaction();
		UserEntity currentUser = (UserEntity) SystemParameter.get(SystemParameter.USER);
		List<HistoryEntity> historyList = mController.executeNamedQuery(HistoryEntity.class, Entitys.HISTORY, user.getUuid().toString(), currentUser.getUuid().toString());
		mController.commitTransaction();
		return historyList;
	}
}
