package gui.helper;

import actions.MessageTO;
import connections.ClientMessages;
import connections.rest.FileAccess;
import database.Entitys;
import database.memorydatabase.DataBaseController;
import database.memorydatabase.FileEntity;
import database.memorydatabase.HistoryEntity;
import database.memorydatabase.UserEntity;
import gui.MainFrame;
import gui.UserHistoryPanel;

public class Message extends Thread{
	
	private ClientMessages client;
	private UserHistoryPanel userHistory;
	private MainFrame mainFrame;
	private DataBaseController mController;
	
	public Message(UserHistoryPanel userHistory, MainFrame mainFrame) {
		client = new ClientMessages();
		this.userHistory = userHistory;
		this.mainFrame = mainFrame;
		mController = new DataBaseController();
	}
	
	@Override
	public void run() {
		client.openConnection();

		while (true) {
			MessageTO messageTO = client.readObject();
			HistoryEntity history = convert(messageTO);
			if (!checkIfExist(history)) {
				save(history);
				userHistory.addElement(history);
			}
			// User 29 wrz 2016 zrobic oznaczenie na liscie znajomych o
			// otrzymaniu nowego komunikatu
		}
	}

	private HistoryEntity convert(MessageTO messageTO) {
		HistoryEntity history = null;
		if (messageTO.getIsFile()) {

			String fileUuid = messageTO.getFileUuid();
			FileEntity file = new FileEntity(messageTO.getFileUuid(), messageTO.getFileName());
			
			history = new HistoryEntity(messageTO.getContent(), messageTO.getDate(), messageTO.getIsFile(), findUser(messageTO.getUserFrom()), file);
			FileAccess.downloadFiles(fileUuid, messageTO.getFileName());
			// User 29 wrz 2016 dorzucic plik do panelu po prawej
		} else {

			history = new HistoryEntity(messageTO.getContent(), messageTO.getDate(), messageTO.getIsFile(), findUser(messageTO.getUserFrom()));

		}
		return history;
	}
	
	private void save(Entitys history) {
		try {
			mController.beginTransaction();
			mController.saveToDataBase(history);
			mController.commitTransaction();
		} catch (RuntimeException e) {
			
		}
	}
	
	private UserEntity findUser(String uuid) {
		mController.beginTransaction();
		UserEntity user = mController.findByPrimaryKey(UserEntity.class, uuid);
		mController.commitTransaction();
		return user;
	}
	
	private boolean checkIfExist(HistoryEntity history) {
		mController.beginTransaction();
		FileEntity file = mController.findByPrimaryKey(FileEntity.class, history.getFileEntity().getUuid());
		mController.rollbackTransaction();
		
		return file != null ? true : false;
	}

	
}
