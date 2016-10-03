package gui;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import database.memorydatabase.FileEntity;
import database.memorydatabase.HistoryEntity;
import database.memorydatabase.UserEntity;
import masterdata.SystemParameter;
import java.awt.Component;

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
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut);

		panel.add(new JLabel(user.getLogin()));
		panel.add(Box.createHorizontalStrut(20));
		
		panel.add(new JLabel(user.getImie()));
		panel.add(Box.createHorizontalStrut(20));
		
		panel.add(new JLabel(history.getDate().toString()));
		panel.add(Box.createHorizontalStrut(20));
		
		String message = history.getContent();
		if(message != null && message.length() > 0) {
			panel.add(new JLabel(message));
		}
		
		if(history.isFile()){
			FileEntity file = history.getFileEntity();
			panel.add(new JLabel(file.getName()));
		}

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut_1);
		return panel;
	}
}