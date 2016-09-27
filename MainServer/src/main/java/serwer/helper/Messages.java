package serwer.helper;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.UUID;

import actions.Actions;
import actions.MessageTO;
import database.DataBaseController;
import database.Message;
import masterdata.DataHelper;

public class Messages {

	private ObjectInputStream objectInputStream;
	private ObjectOutputStream objectOutputStream;
	private UUID user;

	public Messages(ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream, UUID user) {
		this.objectInputStream = objectInputStream;
		this.objectOutputStream = objectOutputStream;
		this.user = user;
	}

	public void runMessages() {
		Thread send = new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						sendMessages();
						sleep(DataHelper.TIME_DELAY);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		};
		send.start();

		Thread receive = new Thread() {
			@Override
			public void run() {
				try {
					while (true) {
						receiveMessages();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		receive.start();
	}

	private void sendMessages() throws IOException {
		DataBaseController mController = new DataBaseController();
		while(mController.transactionIsActive());
		mController.beginTransaction();
		List<Message> messages = mController.executeNamedQuery(Message.class, Actions.MESSAGE_FOR_USER, user.toString());
		mController.commitTransaction();
		
		for (Message message : messages) {
			MessageTO messageTO = new MessageTO(message);
			objectOutputStream.writeObject(messageTO);
		}
	}

	private void receiveMessages() throws Exception {
		DataBaseController mController = new DataBaseController();
		MessageTO message = (MessageTO) objectInputStream.readObject();
		while (mController.transactionIsActive());

		mController.beginTransaction();
		mController.saveToDataBase(message.getEntity());
		mController.commitTransaction();
	}
}
