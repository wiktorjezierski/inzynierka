package starter;


import serwer.Serwer;

public class Starter {

	public static void main(String[] args) throws Exception {

		Thread t = new Serwer();
		t.start();
	}

}
