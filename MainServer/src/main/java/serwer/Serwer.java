package serwer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import database.DataBaseController;
import masterdata.DataHelper;

public class Serwer extends Thread {

	private static int port = DataHelper.DEFAULT_PORT;
	private ServerSocket serverSocket;
	private DataOutputStream out;
	private DataBaseController mController;

	public Serwer() throws IOException {
		mController = new DataBaseController();
		mController.openConnection();
		serverSocket = new ServerSocket(port);
	}

	public void run() {
		while (true) {
			openConnection();
		}
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

			Thread t = new ServerAction(port, mController);
			t.start();

		} catch (SocketTimeoutException s) {
			System.out.println("Socket timed out!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
