package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.Panel;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import database.User;

public class UserDescription extends MainPanel {

	private static final long serialVersionUID = 1L;
	
	private User user;
	private UserHistoryPanel userHistory;
	private UserDescriptionBottom userDescriptionBottom;
	
	private Label picture;
	private Label login;
	private JLabel name;
	private JLabel surname;
	private JLabel lastAccessDate;
	private JTextField input;

	/**
	 * Create the panel.
	 */
	public UserDescription(MainFrame mainFrame) {
		super(mainFrame);
		this.mainFrame = mainFrame;
		setLayout(new BorderLayout(0, 0));
		
		Panel panel = new Panel();
		panel.setMinimumSize(new Dimension(100, 50));
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		Panel panel_2 = new Panel();
		panel.add(panel_2, BorderLayout.WEST);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		Component verticalStrut = Box.createVerticalStrut(20);
		panel_2.add(verticalStrut);
		
		picture = new Label("New label");
		panel_2.add(picture);
		
		Component verticalGlue = Box.createVerticalGlue();
		panel_2.add(verticalGlue);
		
		login = new Label("New label");
		panel_2.add(login);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		panel_2.add(verticalStrut_1);
		
		Panel panel_3 = new Panel();
		panel.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		panel_3.add(horizontalStrut_2);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		panel_3.add(horizontalStrut_3);
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		panel_3.add(horizontalStrut_4);
		
		name = new JLabel("imie");
		panel_3.add(name);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		panel_3.add(horizontalStrut_1);
		
		surname = new JLabel("nazwisko");
		panel_3.add(surname);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		panel_3.add(horizontalStrut);
		
		lastAccessDate = new JLabel("data");
		panel_3.add(lastAccessDate);
		
		userHistory = new UserHistoryPanel(mainFrame);
		userDescriptionBottom = new UserDescriptionBottom(userHistory);
		add(userDescriptionBottom, BorderLayout.SOUTH);
	}

	public void setValue(User user) {
		this.user = user;
		
		picture.setText("NEXT VERSION");
		login.setText(user.getLogin());
		name.setText(user.getImie());
		surname.setText(user.getNazwisko());
		lastAccessDate.setText("NEXT VERSION");
		
		createHistoryDescription(user);
		userDescriptionBottom.setUser(user);
	}
	
	private void createHistoryDescription(User user) {
		add(userHistory, BorderLayout.CENTER);
		userHistory.generateHistory(user);
	}
	
}
