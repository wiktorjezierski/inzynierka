package starter;


import serwer.Serwer;
import serwer.SerwerMessages;

public class Starter {

	public static void main(String[] args) throws Exception {

		Thread mainSerwer = new Serwer();
		mainSerwer.start();
		
		Thread serwerMessage = new SerwerMessages();
		serwerMessage.start();
		
	}

}
