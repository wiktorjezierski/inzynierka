package serwer.helper;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
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
	private ServerSocket socket;
	private UUID user;

	public Messages(ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream, ServerSocket socket ,UUID user) throws IOException {
		this.objectInputStream = objectInputStream;
		this.objectOutputStream = objectOutputStream;
		this.socket = socket;
		this.user = user;
	}

	public void runMessages() {
		Thread send = new Thread() {
			@Override
			public void run() {
				try {
					while (true) {
						sendMessages();
						sleep(DataHelper.TIME_DELAY);
					}
				} catch (Exception e) {
					e.printStackTrace();
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

	private void sendMessages() {
		DataBaseController mController = new DataBaseController();
		try {
			while (mController.transactionIsActive());
			mController.beginTransaction();
			List<Message> messages = mController.executeNamedQuery(Message.class, Actions.MESSAGE_FOR_USER, user.toString());

			for (Message message : messages) {
				MessageTO messageTO = new MessageTO(message);
				objectOutputStream.writeObject(messageTO);
				mController.remove(message);
			}
			mController.commitTransaction();
		} catch (IOException e) {
			mController.rollbackTransaction();
			e.printStackTrace();
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
