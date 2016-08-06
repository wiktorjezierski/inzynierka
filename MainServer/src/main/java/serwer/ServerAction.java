package serwer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import actions.Actions;
import actions.Response;
import database.DataBaseController;

public class ServerAction extends Thread {
	
	private final static int TIME_OUT = 20000;
	
	private ServerSocket serverSocket;
	private ObjectInputStream objectInputStream;
	private ObjectOutputStream objectOutputStream;
	private DataBaseController mController;
	
	public ServerAction(int port, DataBaseController controller) throws IOException {
		serverSocket = new ServerSocket(port);
		serverSocket.setSoTimeout(TIME_OUT);
		mController = controller;
	}

	public void run() {
		openConnection();
	}
	
	public void openConnection() {
		try {
			System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
			Socket server = serverSocket.accept();
			System.out.println("Just connected to " + server.getRemoteSocketAddress());
			
			objectInputStream = new ObjectInputStream(server.getInputStream());
			objectOutputStream = new ObjectOutputStream(server.getOutputStream());
			
			while (true) {
				Actions actions = (Actions) objectInputStream.readObject();
				while(mController.transactionIsActive());	// TODO: USER change using transaction
				Response response = actions.run();			// TODO: USER add ip as parameter
				objectOutputStream.writeObject(response);
			}
			
		} catch (SocketTimeoutException s) {
			System.out.println("Socket timed out!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
