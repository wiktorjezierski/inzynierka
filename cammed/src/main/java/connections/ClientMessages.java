package connections;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.UUID;

import masterdata.SystemParameter;

public class ClientMessages {
	
	private static Socket client;
	
	private String serverAddress;
	private int port;
	
	private static ObjectInputStream objectInputStream;
	private static ObjectOutputStream objectOutputStream;
	
	public ClientMessages(String serverAddress, int port) {
		this.serverAddress = serverAddress;
		this.port = port;
	}
	
	public void openConnection() {
		try {
			client = new Socket(serverAddress, port);
			objectOutputStream = new ObjectOutputStream(client.getOutputStream());
			objectInputStream = new ObjectInputStream(client.getInputStream());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private UUID retrieveUUID() {
		
		Object user = SystemParameter.get(SystemParameter.USER);
		return null;
	}
}
