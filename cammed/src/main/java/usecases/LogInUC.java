package usecases;

import actions.DeviceType;
import actions.Response;
import actions.SignInAction;
import connections.Client;
import gui.GuiTO;
import gui.JOptionPaneDoubleInput;

public class LogInUC implements UseCase {

	public void execute() {
		Response response = new Response(false);
		try {

			Client client = Client.connectWithMainSerwer();

			do {
				GuiTO guiTO = JOptionPaneDoubleInput.showMultipleInputDialog();
				if (guiTO.getValue1().length() == 0 || guiTO.getValue2().length() == 0)
					continue;

				SignInAction logowanie = new SignInAction(guiTO.getValue1(), guiTO.getValue2(), true, DeviceType.PC);
				client.writeObject(logowanie);
				response = (Response) client.readObject();
			} while (!response.isConfirmation());

			return;
		} catch (Exception e) {
		}
	}
}
