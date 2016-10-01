package gui;

import java.awt.ScrollPane;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import database.User;
import database.memorydatabase.HistoryEntity;
import gui.helper.Controller;
import gui.helper.Message;

public class UserHistoryPanel extends MainPanel {

	private static final long serialVersionUID = -5915173622961906936L;

	private User user;
	private Controller controller;
	
	private JPanel panel;
	
	/**
	 * Create the panel.
	 */
	public UserHistoryPanel(MainFrame mainFrame) {
		super(mainFrame);
		controller = new Controller();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		ScrollPane scrollPane = new ScrollPane();
		add(scrollPane);
		
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		scrollPane.add(panel);
		
		runReceiveMessages();

	}

	public void generateHistory(User user) {
		this.user = user;
		panel.removeAll();
		List<HistoryEntity> historyList = controller.generateHistoryDetails(user);

		for (HistoryEntity history : historyList) {
			addElement(history);
		}
	}

	public synchronized void addElement(HistoryEntity history) {
		panel.add(new UserHistoryDetail(history));
		panel.repaint();
		panel.revalidate();
	}
	
	private void runReceiveMessages() {
		Thread message = new Message(this, mainFrame);
		message.start();
	}
}
