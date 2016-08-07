package gui;

import actions.DeviceType;
import actions.Response;
import actions.SignInAction;
import connections.Client;
import connections.DataHelper;

public class Logowanie {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Logowanie logowanie = new Logowanie();
		logowanie.validate(JOptionPaneDoubleInput.showMultipleInputDialog());
	}

	private void validate(GuiTO guiTO) {
		try {
			Client client = new Client(DataHelper.MAIN_SERVER_ADDRESS, DataHelper.MAIN_SERVER_PORT);
			client.openConnection();

			SignInAction logowanie = new SignInAction(guiTO.getValue1(), guiTO.getValue2(), true, DeviceType.PC);
			client.writeObject(logowanie);
			Response response = (Response) client.readObject();

			if (response.isConfirmation()) {

			}
		} catch (Exception e) {

		}
	}
}
