package connections;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
	
	public ClientMessages(String serverAddress, int port) {
		this.serverAddress = serverAddress;
		this.port = port;
	}
	
	@Override
	public void run() {
		openConnection();
	}
	
	public void openConnection() {
		try {
			if (client == null || objectOutputStream == null || objectInputStream == null) {
				client = new Socket(serverAddress, port);
				objectOutputStream = new ObjectOutputStream(client.getOutputStream());
				objectInputStream = new ObjectInputStream(client.getInputStream());
				
				objectOutputStream.writeObject(retrieveUUID());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private UUID retrieveUUID() {
		UserEntity user = (UserEntity) SystemParameter.get(SystemParameter.USER);
		return UUID.fromString(user.getUuid());
	}
	
	public void sendObject(MessageTO message) {
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
