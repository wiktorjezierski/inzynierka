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
	private DataBaseController mController;
	private UUID user;

	public Messages(ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream, UUID user) {
		this.objectInputStream = objectInputStream;
		this.objectOutputStream = objectOutputStream;
		this.user = user;
		mController = new DataBaseController();
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
				while (true) {
					try {
						receiveMessages();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		};
		receive.start();
	}

	private void sendMessages() throws IOException {
		mController.beginTransaction();
		List<Message> messages = mController.executeNamedQuery(Message.class, Actions.MESSAGE_FOR_USER, user.toString());
		mController.commitTransaction();
		MessageTO messageTO = new MessageTO(messages);
		objectOutputStream.writeObject(messageTO);
	}

	private void receiveMessages() throws Exception {
		Message message = (Message) objectInputStream.readObject();
		while (mController.transactionIsActive());

		mController.beginTransaction();
		mController.saveToDataBase(message);
		mController.commitTransaction();
	}
}
