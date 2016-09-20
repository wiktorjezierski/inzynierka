package serwer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import serwer.helper.Messages;

public class SerwerMessages extends Thread {

    static final int PORT = 6060;
    
    ServerSocket serverSocket = null;
    Socket socket = null;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;

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
                
                // new thread for a client
                message = new Messages(objectInputStream, objectOutputStream);
                message.runMessages();
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
        }
    }
	
	
}
