package gui;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import database.memorydatabase.FileEntity;
import database.memorydatabase.HistoryEntity;
import masterdata.SystemParameter;

public class UserHistoryDetail extends MainPanel {

	private static final long serialVersionUID = -4796674936801942164L;

	public UserHistoryDetail(HistoryEntity history) {
		super();
		setLayout(new BorderLayout(0, 0));
		
		if(SystemParameter.get(SystemParameter.MY_LOGIN).equals(history.getUserBean().getLogin())){
			JPanel panel = displaySentMessage(history);
			add(panel, BorderLayout.EAST);
			if(history.isFile()){
				JPanel panel2 = displaySentFile(history);
				add(panel2, BorderLayout.SOUTH);
			}
		} else {
			JPanel panel = displayReceivedMessage(history);
			add(panel, BorderLayout.WEST);
			if(history.isFile()){
				JPanel panel2 = displayReceivedFile(history);
				add(panel2, BorderLayout.SOUTH);
			}
		}
	}

	private JPanel displayReceivedMessage(HistoryEntity history) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		panel.add(Box.createHorizontalStrut(20));

		String message = history.getContent();
		if(message != null && message.length() > 0) {
			panel.add(new JLabel(setColor(message, Colors.GREEN)));
		}
		
		panel.add(Box.createHorizontalStrut(20));
		
		String date = history.getFormatedDate();
		panel.add(new JLabel(setColor(date, Colors.GRAY)));
		panel.add(Box.createHorizontalStrut(20));
		
		panel.add(Box.createHorizontalStrut(20));
		return panel;
	}
	
	private JPanel displayReceivedFile(HistoryEntity history) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		panel.add(Box.createHorizontalStrut(20));

		if (history.isFile()) {
			FileEntity file = history.getFileEntity();
			panel.add(new JLabel(file.getName()));
		}

		panel.add(Box.createHorizontalStrut(20));

		String date = history.getFormatedDate();
		panel.add(new JLabel(setColor(date, Colors.GRAY)));
		panel.add(Box.createHorizontalStrut(20));
		
		panel.add(Box.createHorizontalStrut(20));
		return panel;
	}

	private JPanel displaySentMessage(HistoryEntity history) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		panel.add(Box.createHorizontalStrut(20));

		String date = history.getFormatedDate();
		panel.add(new JLabel(setColor(date, Colors.GRAY)));
		panel.add(Box.createHorizontalStrut(20));
		
		String message = history.getContent();
		if(message != null && message.length() > 0) {
			panel.add(new JLabel(setColor(message, Colors.BLUE)));
		}
		
		panel.add(Box.createHorizontalStrut(20));
		return panel;
	}
	
	private JPanel displaySentFile(HistoryEntity history) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		panel.add(Box.createHorizontalStrut(60));

		String date = history.getFormatedDate();
		
		panel.add(Box.createHorizontalStrut(40));
		
		FileEntity file = history.getFileEntity();
		panel.add(new JLabel(file.getName()));

		panel.add(Box.createHorizontalStrut(40));
		
		panel.add(new JLabel(setColor(date, Colors.GRAY)));
		panel.add(Box.createHorizontalStrut(20));
		
		panel.add(Box.createHorizontalStrut(20));
		return panel;
	}
}