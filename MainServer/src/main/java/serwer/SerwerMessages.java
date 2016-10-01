package serwer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;

import serwer.helper.Messages;

public class SerwerMessages extends Thread {

    static final int PORT = 6060;
    
    private ServerSocket serverSocket = null;
    private Socket socket = null;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    Messages message;
    
    public SerwerMessages() {
		super();
		try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public void run() {
        while (true) {
            try {
                socket = serverSocket.accept();
                objectInputStream = new  ObjectInputStream(socket.getInputStream());
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                UUID userUUID = (UUID) objectInputStream.readObject();
                
                // new thread for a client
                message = new Messages(objectInputStream, objectOutputStream, serverSocket, userUUID);
                message.runMessages();
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            } catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
        }
    }
	
	
}
