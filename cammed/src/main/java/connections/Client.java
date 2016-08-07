package connections;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import actions.Actions;
import actions.Response;

public class Client {
	
	private Socket client;
	private Socket clientAction;
	
	private String serverAddress;
	private int port;
	
	private ObjectInputStream objectInputStream;
	private ObjectOutputStream objectOutputStream;
	
	public Client(String serverAddress, int port) {
		this.serverAddress = serverAddress;
		this.port = port;
	}

	public void openConnection() {
		try {
			System.out.println("Connecting to " + serverAddress + " on port " + port);
			client = new Socket(serverAddress, port);
			System.out.println("Just connected to " + client.getRemoteSocketAddress());
			OutputStream outToServer = client.getOutputStream();
			DataOutputStream out = new DataOutputStream(outToServer);
			out.writeUTF("Hello from " + client.getLocalSocketAddress());
			InputStream inFromServer = client.getInputStream();
			DataInputStream in = new DataInputStream(inFromServer);
			int newPort = Integer.parseInt(in.readUTF());
			System.out.println("Server says " + newPort + " " + client.getRemoteSocketAddress());
			
			clientAction = new Socket(serverAddress, newPort);
			objectOutputStream =  new ObjectOutputStream(clientAction.getOutputStream());
			objectInputStream =  new ObjectInputStream(clientAction.getInputStream());
			
		} catch(Exception e) {
			throw new RuntimeException("something was wrong during initialize connection");
		}
	}
	
	public void writeObject(Actions object) throws IOException {
		objectOutputStream.writeObject(object);
	}
	
	public Response readObject() throws IOException, ClassNotFoundException {
		return (Response) objectInputStream.readObject();
	}
}
