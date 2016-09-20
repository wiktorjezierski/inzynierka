package starter;


import serwer.Serwer;

public class Starter {

	public static void main(String[] args) throws Exception {

		Thread mainSerwer = new Serwer();
		mainSerwer.start();
		
		Thread serwerMessage = new Thread();
		serwerMessage.start();
	}

}
