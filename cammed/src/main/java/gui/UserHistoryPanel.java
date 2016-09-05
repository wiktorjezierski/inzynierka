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
import masterdata.SystemParameter;

public class UserHistoryPanel extends JPanel {

	private static final long serialVersionUID = -5915173622961906936L;

	private JPanel panel;
	
	/**
	 * Create the panel.
	 */
	public UserHistoryPanel() {
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
		UserEntity currentUser = (UserEntity) SystemParameter.get(SystemParameter.USER);
		List<HistoryEntity> historyList = mController.executeNamedQuery(HistoryEntity.class, Entitys.HISTORY, user.getUuid().toString(), currentUser.getUuid().toString());
		mController.commitTransaction();
		
		for (HistoryEntity history : historyList) {
			addElement(history);
		}
	}

	public synchronized void addElement(HistoryEntity history) {
		panel.add(new Detail(history));
		panel.repaint();
		panel.revalidate();
	}
	
}

class Detail extends JPanel {

	private static final long serialVersionUID = -4796674936801942164L;

	public Detail(HistoryEntity history) {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = generateContent(history);
		if(SystemParameter.get(SystemParameter.MY_LOGIN).equals(history.getUserBean().getLogin())){
			add(panel, BorderLayout.EAST);
		} else {
			add(panel, BorderLayout.WEST);
		}
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
