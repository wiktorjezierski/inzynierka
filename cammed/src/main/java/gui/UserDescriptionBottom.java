package gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserDescriptionBottom extends JPanel {

	private static final long serialVersionUID = -441887018410088046L;
	
	private JTextField input;
	private JButton send;
	private UserDescription userDescription;
	
	/**
	 * Create the panel.
	 */
	public UserDescriptionBottom(UserDescription userDescription) {
		this.userDescription = userDescription;
		setLayout(new BorderLayout(0, 0));

		input = new JTextField();
		add(input, BorderLayout.CENTER);
		input.setColumns(10);

		send = new JButton("   Send   ");
		send.addMouseListener(mouseEvent());
		add(send, BorderLayout.EAST);
	}

	private MouseAdapter mouseEvent() {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String enteredText = input.getText();
				input.setText("");
			}
		};
	}

}
