package gui;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import actions.Response;
import actions.SignUPAction;
import connections.Client;

public class SignUpPanel extends MainPanel {

	private static final String BAD_MESSAGE = "<html><font color='red'>not matched passwords</font></html>";
	private static final String OK_MESSAGE = "<html><font color='green'>ok</font></html>";

	private static final long serialVersionUID = 1L;
	private JTextField login;
	private JPasswordField password;
	private JPasswordField password2;
	private JTextField name;
	private JTextField surname;

	private boolean passwordOk;

	private JLabel info;

	/**
	 * Create the panel.
	 */
	public SignUpPanel(MainFrame mainFrame) {
		super(mainFrame);
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setBounds(123, 26, 63, 14);
		add(lblNewLabel);

		login = new JTextField();
		login.setBounds(196, 23, 86, 20);
		add(login);
		login.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(123, 59, 63, 14);
		add(lblNewLabel_1);

		password = new JPasswordField();
		password.setBounds(196, 56, 86, 20);
		add(password);
		password.setColumns(10);

		password2 = new JPasswordField();
		password2.addKeyListener(checkPassword());
		password2.setBounds(196, 91, 86, 20);
		password2.setColumns(10);
		add(password2);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(123, 94, 63, 14);
		add(lblPassword);

		name = new JTextField();
		name.setBounds(196, 127, 86, 20);
		name.setColumns(10);
		add(name);

		JLabel lblImi = new JLabel("Imię");
		lblImi.setBounds(123, 130, 63, 14);
		add(lblImi);

		surname = new JTextField();
		surname.setBounds(196, 169, 86, 20);
		surname.setColumns(10);
		add(surname);

		JLabel lblNazwisko = new JLabel("Nazwisko");
		lblNazwisko.setBounds(123, 172, 63, 14);
		add(lblNazwisko);

		JButton btnZarejestruj = new JButton("Zarejestruj");
		btnZarejestruj.addMouseListener(signUp());
		btnZarejestruj.setBounds(98, 208, 85, 23);
		add(btnZarejestruj);

		JButton btnAnuluj = new JButton("Anuluj");
		btnAnuluj.addMouseListener(cancel());
		btnAnuluj.setBounds(218, 208, 86, 23);
		add(btnAnuluj);

		info = new JLabel("");
		info.setBounds(292, 94, 148, 14);
		add(info);
	}

	public KeyAdapter checkPassword() {
		return new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				char[] pass1 = password.getPassword();
				char[] pass2 = password2.getPassword();

				if (pass1.length != pass2.length) {
					info.setText(BAD_MESSAGE);
					passwordOk = false;
					return;
				}

				for (int i = 0; i < pass2.length; i++) {
					if (pass1[i] != pass2[i]) {
						info.setText(BAD_MESSAGE);
						passwordOk = false;
						return;
					}
				}
				info.setText(OK_MESSAGE);
				passwordOk = true;
			}
		};
	}

	public MouseAdapter cancel() {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mainFrame.displayJPanel(MainFrame.SIGN_UP, MainFrame.LOGIN, BorderLayout.CENTER);
			}
		};
	}

	public MouseAdapter signUp() {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!passwordOk) {
					JOptionPane.showMessageDialog(null, "not matched password");
					return;
				}

				Client client = Client.connectWithMainSerwer();
				SignUPAction signUp = new SignUPAction(login.getText(), password.getText(), name.getText(),
						surname.getText(), false);
				try {
					client.writeObject(signUp);
					Response response = (Response) client.readObject();
					if(response.isConfirmation()) {
						JOptionPane.showMessageDialog(null, "Congratulations");
						mainFrame.displayJPanel(MainFrame.SIGN_UP, MainFrame.LOGIN, BorderLayout.CENTER);
					} else {
						JOptionPane.showMessageDialog(null, "Something went wrong/ntry again");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
	}
}
