package starter;

import java.awt.EventQueue;

import gui.MainFrame;

public class Starter {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		LogInUC logIn = new LogInUC();
//		logIn.execute();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
