package gui;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import actions.Response;
import usecases.LogInUC;

public class LoginPanel extends MainPanel {

	private static final String BAD_MESSAGE = "<html><font color='red'>wprowadź poprawne dane logowania</font></html>";
	
	private static final long serialVersionUID = 1L;
	
	private JTextField loginField;
	private JPasswordField passwordField;
	

	/**
	 * Create the panel.
	 */
	public LoginPanel(MainFrame frame) {
		super(frame);
		setLayout(null);
		
		loginField = new JTextField();
		loginField.setText("");
		loginField.setBounds(210, 71, 123, 20);
		add(loginField);
		loginField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(210, 104, 123, 20);
		add(passwordField);
		passwordField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setBounds(124, 74, 46, 14);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Hasło");
		lblNewLabel_1.setBounds(124, 107, 46, 14);
		add(lblNewLabel_1);

		JButton zaloguj = new JButton("zaloguj");

		zaloguj.addMouseListener(mouseLogowanie);
		zaloguj.setBounds(210, 135, 123, 23);
		add(zaloguj);

		JButton zarejestruj = new JButton("zarejestruj");
		zarejestruj.addMouseListener(mouseRejestracja);
		zarejestruj.setBounds(210, 200, 123, 23);
		add(zarejestruj);
		
		clear();

	}

	private void clear() {
		loginField.setText("");
		passwordField.setText("");
	}

	MouseAdapter mouseRejestracja = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			mainFrame.displayJPanel(Panels.LOGIN, Panels.SIGN_UP, BorderLayout.CENTER);
		}
	};

	@SuppressWarnings("deprecation")
	MouseAdapter mouseLogowanie = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			LogInUC loginUC = new LogInUC();
			String login = loginField.getText();
			String password = passwordField.getText();

			if (login.length() == 0 || password.length() == 0) {
				JOptionPane.showMessageDialog(null, BAD_MESSAGE);
				return;
			}
			
			Response result = loginUC.execute(login, password);
			if (result.isConfirmation()) {
				mainFrame.generateMainGuiDesign(result.getUsers());
			} else {
				JOptionPane.showMessageDialog(null, BAD_MESSAGE);
			}
		}
	};
	
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
//		clear();
	}
}
