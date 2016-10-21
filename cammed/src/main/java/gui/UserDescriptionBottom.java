package gui;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
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
	private JButton chooseFile;
	private UserHistoryPanel userHistory;
	private boolean isChoosenFile;
	private File selectedFile;
	
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
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.EAST);
		
		chooseFile = new JButton("choose file");
		chooseFile.addMouseListener(chooseFileMouseEvent());
		panel.add(chooseFile);

		send = new JButton("   Send   ");
		panel.add(send);
		send.addMouseListener(mouseEvent());

	}

	private MouseAdapter chooseFileMouseEvent() {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					isChoosenFile = true;
					selectedFile = fileChooser.getSelectedFile();
					chooseFile.setText(selectedFile.getName());
				}
				else {
					isChoosenFile = false;
					selectedFile = null;
					chooseFile.setText("choose file");
				}
			}
		};
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	private MouseAdapter mouseEvent() {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String enteredText = input.getText();
				
				if(isChoosenFile){
					controller.sendMessage(enteredText, user, userHistory, selectedFile);
					controller.copyFile(selectedFile);
					isChoosenFile = false;
					chooseFile.setText("choose file");
				}
				else {
					controller.persistMessage(enteredText, userHistory);
					controller.sendMessage(enteredText, user);
				}
				
				input.setText("");
			}
		};
	}

}
