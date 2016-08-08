package connections;

import java.awt.Image;
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
import multimedia.video.ImageBuffer;

public class Client {
	
	private Socket client;
	private Socket clientAction;
	
	private String serverAddress;
	private int port;
	
	private InputStream inputStream;
	private OutputStream outputStream;
	
	private ObjectInputStream objectInputStream;
	private ObjectOutputStream objectOutputStream;
	
	public Client(String serverAddress, int port) {
		this.serverAddress = serverAddress;
		this.port = port;
	}
	
	public static Client connectWithMainSerwer() {
		Client client = new Client(DataHelper.MAIN_SERVER_ADDRESS, DataHelper.MAIN_SERVER_PORT);
		client.openConnection(true);
		return client;
	}

	public void openConnection(boolean mainSerwer) {
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
			
			inputStream = client.getInputStream();
			outputStream = client.getOutputStream();
			
			if (mainSerwer) {
				clientAction = new Socket(serverAddress, newPort);
				objectOutputStream = new ObjectOutputStream(clientAction.getOutputStream());
				objectInputStream = new ObjectInputStream(clientAction.getInputStream());
			} else {
				objectOutputStream = new ObjectOutputStream(client.getOutputStream());
				objectInputStream = new ObjectInputStream(client.getInputStream());
			}
			
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
	
	public Image receiveImage() {
		try{
			ImageBuffer imageBuffer = (ImageBuffer) objectInputStream.readObject();
			return imageBuffer.getImage();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void sendImage(ImageBuffer image) {
		try{
			objectOutputStream.writeObject(image);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public byte[] receiveAudio() {
		byte[] data = new byte[DataHelper.AUDIO_CHUNK_SIZE];
		try {
			inputStream.read(data, 0, DataHelper.AUDIO_CHUNK_SIZE);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(data.length > 0){
			return data;
		} else {
			return new byte[DataHelper.AUDIO_CHUNK_SIZE];
		}
	}
	
	public void sendAudio(byte [] bytes) {
		try {
			outputStream.write(bytes, 0, bytes.length);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
