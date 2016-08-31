package gui;

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
		scrollPane.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

	}
	
	public void generateHistory(User user) {
		DataBaseController mController = new DataBaseController();
		mController.beginTransaction();
		List<HistoryEntity> historyList = mController.findAll(HistoryEntity.class);
		mController.commitTransaction();
		
		for (HistoryEntity history : historyList) {
			panel.add(new Detail(history));
		}
	}

}

class Detail extends JPanel {

	private static final long serialVersionUID = -4796674936801942164L;

	public Detail(HistoryEntity history) {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		UserEntity user = history.getUserBean();

		add(new JLabel(user.getLogin()));
		add(Box.createHorizontalStrut(20));
		
		add(new JLabel(user.getImie()));
		add(Box.createHorizontalStrut(20));
		
		add(new JLabel(history.getDate().toString()));
		add(Box.createHorizontalStrut(20));
		
		add(new JLabel(history.getContent()));
	}
	
}

