package tests;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import actions.DeviceType;
import actions.Response;
import actions.SignInAction;
import actions.SignUPAction;

public class Tests {
	private static Socket client;

	public static void main(String[] args) {
//		for (int i = 0; i < 5; i++) {
			tests(1);
//		}
	}

	private static void tests(int i) {
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
			int newPort = Integer.parseInt(in.readUTF());
			System.out.println("Server says " + newPort + " " + client.getRemoteSocketAddress());
			
			client = new Socket(serverName, newPort);

			ObjectOutputStream objectOutputStream = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream objectInputStream  = new ObjectInputStream(client.getInputStream());
//			
//			SignUPAction rejestracja = new SignUPAction("wjeziorko6", "admin123", "wiktor", "jezierski", true);
//			objectOutputStream.writeObject(rejestracja);
//			Response response = (Response)objectInputStream.readObject();
			
			SignInAction logowanie = new SignInAction("wjeziorko6", "admin123", true, DeviceType.PC);
			objectOutputStream.writeObject(logowanie);
			Response response = (Response)objectInputStream.readObject();

			Thread.sleep(3000);
			objectOutputStream.close();
			client.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
