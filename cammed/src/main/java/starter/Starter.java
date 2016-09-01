package starter;

import java.awt.EventQueue;

import actions.DeviceType;
import database.memorydatabase.DataBaseController;
import gui.MainFrame;
import usecases.ReceiveConnectionUC;
import usecases.UseCase;

public class Starter {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		Thread database = new Thread() {
			public void run() {
				DataBaseController mController = new DataBaseController();
				mController.openConnection();
			}
		};
		database.start();
		
		Thread serwer = new Thread() {
			public void run() {
				try {
					UseCase useCase = new ReceiveConnectionUC(DeviceType.PC);
					useCase.execute();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		serwer.start();
		
		
		
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
