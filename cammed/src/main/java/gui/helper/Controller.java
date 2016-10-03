package gui.helper;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import actions.CallAction;
import actions.MessageTO;
import actions.Response;
import connections.Client;
import connections.ClientMessages;
import connections.rest.FileAccess;
import database.Entitys;
import database.User;
import database.memorydatabase.DataBaseController;
import database.memorydatabase.FileEntity;
import database.memorydatabase.HistoryEntity;
import database.memorydatabase.UserEntity;
import gui.UserHistoryPanel;
import masterdata.SystemParameter;
import usecases.EstablishConnectionUC;
import usecases.SignOutUC;
import usecases.UseCase;

public class Controller {
	
	private DataBaseController mController;
	private ClientMessages message;
	
	public Controller() {
		mController = new DataBaseController();
		message = new ClientMessages();
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
		UserEntity userEntity = findUser();
		HistoryEntity history = new HistoryEntity(enteredText, userEntity);
		userHistory.addElement(history);
		
		mController.beginTransaction();
		mController.saveToDataBase(history);
		mController.commitTransaction();
	}
	
	private FileEntity persistMessage(String enteredText, UserHistoryPanel userHistory, File file, UserEntity userEntity) {
		FileEntity fileEntity = new FileEntity(parseFileName(file.getName()));
		HistoryEntity history = new HistoryEntity(enteredText, new Date(), true, userEntity, fileEntity);
		userHistory.addElement(history);
		
		mController.beginTransaction();
		mController.saveToDataBase(history);
		mController.commitTransaction();
		
		return fileEntity;
	}
	
	public List<HistoryEntity> generateHistoryDetails(User user) {
		DataBaseController mController = new DataBaseController();
		mController.beginTransaction();
		UserEntity currentUser = findUser();
		List<HistoryEntity> historyList = mController.executeNamedQuery(HistoryEntity.class, Entitys.HISTORY, user.getUuid().toString(), currentUser.getUuid().toString());
		mController.commitTransaction();
		return historyList;
	}
	
	public void sendMessage(String enteredText, User user, UserHistoryPanel userHistory, File file) {
		UserEntity loggedUser = findUser();
		FileEntity fileEntity = persistMessage(enteredText, userHistory, file, loggedUser);
		
		MessageTO messageTO = new MessageTO(enteredText, true, loggedUser.getUuid().toString() , user.getUuid().toString(), fileEntity.getUuid(), fileEntity.getName());
		message.sendObject(messageTO);
		FileAccess.sendFile(file, fileEntity.getUuid());
	}
	
	public void sendMessage(String enteredText, User user) {
		UserEntity loggedUser = findUser();
		MessageTO messageTO = new MessageTO(enteredText, false, loggedUser.getUuid().toString() , user.getUuid().toString());
		message.sendObject(messageTO);
	}
	
	private UserEntity findUser() {
		return (UserEntity) SystemParameter.get(SystemParameter.USER);
	}
	
	private String parseFileName(String name) {
		String[] split = name.split(Pattern.quote("\\"));
		return split[split.length - 1];
	}
}
