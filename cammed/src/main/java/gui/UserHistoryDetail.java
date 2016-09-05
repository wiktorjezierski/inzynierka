package gui;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import database.memorydatabase.HistoryEntity;
import database.memorydatabase.UserEntity;
import masterdata.SystemParameter;

public class UserHistoryDetail extends JPanel {

	private static final long serialVersionUID = -4796674936801942164L;

	public UserHistoryDetail(HistoryEntity history) {
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