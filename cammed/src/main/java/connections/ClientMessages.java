package connections;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.UUID;

import actions.MessageTO;
import database.memorydatabase.UserEntity;
import masterdata.SystemParameter;

public class ClientMessages extends Thread {
	
	private static Socket client;
	
	private String serverAddress;
	private int port;
	
	private static ObjectInputStream objectInputStream;
	private static ObjectOutputStream objectOutputStream;
	private static OutputStream outputStream;
	
	public ClientMessages() {
		this.serverAddress = DataHelper.MAIN_SERVER_ADDRESS;
		this.port = DataHelper.MAIN_SERVER_MESSAGES_PORT;
	}
	
	@Override
	public void run() {
		openConnection();
	}
	
	public synchronized void openConnection() {
		try {
			if (client == null || objectOutputStream == null || objectInputStream == null || outputStream == null) {
				client = new Socket(serverAddress, port);
				outputStream  = client.getOutputStream();
				objectOutputStream = new ObjectOutputStream(outputStream);
				objectInputStream = new ObjectInputStream(client.getInputStream());
				
				objectOutputStream.writeObject(retrieveUUID());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private UUID retrieveUUID() {
		UserEntity user = null;
		while((user = (UserEntity) SystemParameter.get(SystemParameter.USER)) == null);
		return UUID.fromString(user.getUuid());
	}
	
	public void sendObject(Object message) {
		try {
			objectOutputStream.writeObject(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public MessageTO readObject() {
		try {
			return (MessageTO) objectInputStream.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; 
	}
}
