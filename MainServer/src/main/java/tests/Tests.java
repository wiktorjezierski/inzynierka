package tests;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import actions.SignUP;

public class Tests {
	private static Socket client;

	public static void main(String[] args) {
		String serverName = "192.168.0.4";
//		String serverName = "10.0.2.15";
		int port = 6066;
		try {
			System.out.println("Connecting to " + serverName + " on port " + port);
			client = new Socket(serverName, port);
			System.out.println("Just connected to " + client.getRemoteSocketAddress());
			OutputStream outToServer = client.getOutputStream();
			DataOutputStream out = new DataOutputStream(outToServer);
			out.writeUTF("Hello from " + client.getLocalSocketAddress());
			InputStream inFromServer = client.getInputStream();
			DataInputStream in = new DataInputStream(inFromServer);
			String newPort = in.readUTF();
			System.out.println("Server says " + newPort + " " + client.getRemoteSocketAddress());

			ObjectOutputStream objectOutputStream = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream objectInputStream  = new ObjectInputStream(client.getInputStream());
			
			SignUP rejestracja = new SignUP("wjeziorko", "wiktor", "jezierski", true);
			objectOutputStream.writeObject(rejestracja);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
