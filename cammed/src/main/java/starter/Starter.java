package starter;

import usecases.LogInUC;

public class Starter {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		LogInUC logIn = new LogInUC();
		logIn.execute();
	}
}
