package gui;

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

public class Logowanie {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Logowanie frame = new Logowanie();
		frame.somemethod(JOptionPaneDoubleInput.showMultipleInputDialog());
	}

	private void somemethod(GuiTO guiTO) {
		try {
			Socket socket = openConnection();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream objectInputStream  = new ObjectInputStream(socket.getInputStream());
			
			SignInAction logowanie = new SignInAction(guiTO.getValue1(), guiTO.getValue2(), true, DeviceType.PC);
			objectOutputStream.writeObject(logowanie);
			Response response = (Response)objectInputStream.readObject();
			
			if(response.isConfirmation()){
				
			}
		} catch (Exception e) {
			
		}
	}
	
	private Socket openConnection() {
		String serverName = "192.168.0.4";
		int port = 6066;
		try {
			System.out.println("Connecting to " + serverName + " on port " + port);
			Socket client = new Socket(serverName, port);
			System.out.println("Just connected to " + client.getRemoteSocketAddress());
			OutputStream outToServer = client.getOutputStream();
			DataOutputStream out = new DataOutputStream(outToServer);
			out.writeUTF("Hello from " + client.getLocalSocketAddress());
			InputStream inFromServer = client.getInputStream();
			DataInputStream in = new DataInputStream(inFromServer);
			int newPort = Integer.parseInt(in.readUTF());
			System.out.println("Server says " + newPort + " " + client.getRemoteSocketAddress());
			
			return new Socket(serverName, newPort);
		} catch(Exception e) {
			throw new RuntimeException("something was wrong during initialize connection");
		}
	}
}
