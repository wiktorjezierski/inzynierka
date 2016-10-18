package connections;

import java.awt.Image;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import multimedia.video.ImageBuffer;

public class Serwer extends Thread {

	private ServerSocket serverSocket;
	private Socket serwer;
	private OutputStream outputStream;
	private InputStream inputStream;
	private ObjectOutput objectOutput;
	private ObjectInput objectInput;
	private static int port;

	private Serwer(int port){
		try {
			this.port = port;
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// serverSocket.setSoTimeout(1000);
	}
	
	public static Serwer establishSerwer(int port) {
		Serwer serwer = new Serwer(port);
		serwer.openConnection();
		return serwer;
	}

	private void openConnection() {
		try {
			System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
			serwer = serverSocket.accept();
			System.out.println("Just connected to " + serwer.getRemoteSocketAddress());
			DataInputStream in = new DataInputStream(serwer.getInputStream());
			System.out.println(in.readUTF());
			DataOutputStream out = new DataOutputStream(serwer.getOutputStream());
			out.writeUTF(Integer.toString(++port));
			
			outputStream = serwer.getOutputStream();
			inputStream = serwer.getInputStream();
			
			objectOutput = new ObjectOutputStream(outputStream);
			objectInput = new ObjectInputStream(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closeConnection() {
		try {
			serverSocket.close();
			serwer.close();
			outputStream.close();
			inputStream.close();
			objectOutput.close();
			objectInput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendImage(ImageBuffer temp) throws IOException {
			objectOutput.writeObject(temp);
	}
	
	public Image receiveImage() throws ClassNotFoundException, IOException {
			ImageBuffer imageBuffer = (ImageBuffer) objectInput.readObject();
			return imageBuffer.getImage();
	}
	
	public void sendAudio(byte [] bytes) throws IOException {
			outputStream.write(bytes, 0, bytes.length);
	}
	
	public byte[] receiveAudio() throws IOException {
		byte[] data = new byte[DataHelper.AUDIO_CHUNK_SIZE];
			inputStream.read(data, 0, DataHelper.AUDIO_CHUNK_SIZE);
		
		if(data.length > 0){
			return data;
		} else {
			return new byte[DataHelper.AUDIO_CHUNK_SIZE];
		}
	}
}
