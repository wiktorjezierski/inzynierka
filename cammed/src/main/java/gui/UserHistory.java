package gui;

import java.awt.BorderLayout;
import java.awt.ScrollPane;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import database.Entitys;
import database.User;
import database.memorydatabase.DataBaseController;
import database.memorydatabase.HistoryEntity;
import database.memorydatabase.UserEntity;

public class UserHistory extends JPanel {

	private static final long serialVersionUID = -5915173622961906936L;

	private JPanel panel;
	
	/**
	 * Create the panel.
	 */
	public UserHistory() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		ScrollPane scrollPane = new ScrollPane();
		add(scrollPane);
		
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		scrollPane.add(panel);

	}
	
	public void generateHistory(User user) {
		DataBaseController mController = new DataBaseController();
		mController.beginTransaction();
		List<HistoryEntity> historyList = mController.executeNamedQuery(HistoryEntity.class, Entitys.HISTORY, user.getUuid().toString());
		mController.commitTransaction();
		
		for (HistoryEntity history : historyList) {
			addElement(history, BorderLayout.CENTER);
		}
	}

	public synchronized void addElement(HistoryEntity history, String position) {
		panel.add(new Detail(history, position));
	}
	
}

class Detail extends JPanel {

	private static final long serialVersionUID = -4796674936801942164L;

	public Detail(HistoryEntity history, String position) {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = generateContent(history);
		add(panel, position);
	}

	private JPanel generateContent(HistoryEntity history) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		UserEntity user = history.getUserBean();

		panel.add(new JLabel(user.getLogin()));
		panel.add(Box.createHorizontalStrut(20));
		
		panel.add(new JLabel(user.getImie()));
		panel.add(Box.createHorizontalStrut(20));
		
		panel.add(new JLabel(history.getDate().toString()));
		panel.add(Box.createHorizontalStrut(20));
		
		panel.add(new JLabel(history.getContent()));
		return panel;
	}
}
