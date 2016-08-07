package gui;

import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class JOptionPaneDoubleInput {
	
	private static int sIterator = 0;

	@SuppressWarnings("deprecation")
	public static GuiTO showMultipleInputDialog() {
		JTextField login = new JTextField(5);
		JPasswordField password = new JPasswordField(5);

		JPanel myPanel = new JPanel();
		JPanel myPanel1 = new JPanel();
		JPanel myPanel2 = new JPanel();

		myPanel.setLayout((LayoutManager) new BoxLayout(myPanel, BoxLayout.Y_AXIS));
		
		if(sIterator++ > 0)
			myPanel.add(new JLabel("<html><font color='red'>wprowadź poprawne dane logowania</font></html>"));

		myPanel.add(myPanel1);
		myPanel.add(myPanel2);

		myPanel1.add(new JLabel("login          "));
		myPanel1.add(login);
		myPanel2.add(new JLabel("password "));
		myPanel2.add(password);

		int result = JOptionPane.showConfirmDialog(null, myPanel, "Please Enter data", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			return new GuiTO(login.getText(), password.getText());
		} else {
			System.exit(0);	// TODO: check this place - or throw exception
		}
		return null;
	}
}