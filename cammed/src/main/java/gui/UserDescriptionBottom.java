package gui;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import database.User;
import gui.helper.Controller;

public class UserDescriptionBottom extends JPanel {

	private static final long serialVersionUID = -441887018410088046L;
	
	private User user;
	private Controller controller;
	
	private JTextField input;
	private JButton send;
	private UserHistoryPanel userHistory;
	
	/**
	 * Create the panel.
	 */
	public UserDescriptionBottom(UserHistoryPanel userHistory) {
		this.userHistory = userHistory;
		controller = new Controller();
		
		setLayout(new BorderLayout(0, 0));

		input = new JTextField();
		add(input, BorderLayout.CENTER);
		input.setColumns(10);

		send = new JButton("   Send   ");
		send.addMouseListener(mouseEvent());
		add(send, BorderLayout.EAST);
		
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	private MouseAdapter mouseEvent() {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String enteredText = input.getText();
				
				controller.persistMessage(enteredText, userHistory);
				
				input.setText("");
				// User 4 wrz 2016 dodac tutaj wrzucenie na serwer jesli user jest niezalogowany - wspolna czesc z sygnalizacja plikow
			}
		};
	}

}