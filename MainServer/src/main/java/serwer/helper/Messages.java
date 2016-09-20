package serwer.helper;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Messages {

	ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;
    
	public Messages(ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream) {
		this.objectInputStream = objectInputStream;
		this.objectOutputStream = objectOutputStream;
	}
	
	public void runMessages() {
		Thread send = new Thread() {
			@Override
			public void run() {
				sendMessages();
			}
		};
		send.start();
		
		Thread receive = new Thread() {
			@Override
			public void run() {
				receiveMessages();
			}
		};
		receive.start();
	}
    
	private void sendMessages() {
		
	}
	
	private void receiveMessages() {
		
	}
}
