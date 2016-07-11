package serwer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import actions.Actions;
import actions.DataHelper;
import actions.Response;
import database.DataBaseController;

public class Serwer extends Thread {

	private static int port = 6066;
	private ServerSocket serverSocket;
	private DataOutputStream out;
	private OutputStream stream;
	private ObjectInputStream objectInputStream;
	private ObjectOutputStream objectOutputStream;

	public Serwer() throws IOException {
		DataBaseController dbController = new DataBaseController();
		dbController.openConnection();
		serverSocket = new ServerSocket(port);
	}

	public void run() {
		openConnection();
	}
	
	public void openConnection() {
		try {
			System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
			Socket server = serverSocket.accept();
			System.out.println("Just connected to " + server.getRemoteSocketAddress());
			
			
			DataInputStream in = new DataInputStream(server.getInputStream());
			System.out.println(in.readUTF());
			out = new DataOutputStream(server.getOutputStream());
			out.writeUTF(Integer.toString(++port));
			
			// nowe
			objectInputStream = new ObjectInputStream(server.getInputStream());
			DataHelper.setIp(server.getInetAddress().toString());
			objectOutputStream = new ObjectOutputStream(server.getOutputStream());
			Actions actions = (Actions)objectInputStream.readObject();
			Response response = actions.run();
			objectOutputStream.writeObject(response);
			// nowe

		} catch (SocketTimeoutException s) {
			System.out.println("Socket timed out!");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void send(byte [] bytes) {
		try {
			stream.write(bytes, 0, bytes.length);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
