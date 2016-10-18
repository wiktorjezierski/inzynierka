package starter;

import java.awt.EventQueue;

import actions.DeviceType;
import gui.MainFrame;
import masterdata.SystemParameter;
import usecases.ReceiveConnectionUC;
import usecases.UpdateFiles;
import usecases.UseCase;

public class Starter {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		
		Thread updateFiles = new UpdateFiles();
		updateFiles.start();
		
		Thread serwer = new Thread() {
			public void run() {
				try {
					SystemParameter.put(SystemParameter.VIDEO_INTERVIEW, true);
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
