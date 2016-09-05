package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

import javax.swing.BoxLayout;

import database.User;
import gui.helper.Controller;
import gui.helper.Refresh;

public class FriendsPanel extends MainPanel {

	private static final long serialVersionUID = 1L;
	
	private Controller controller;
	
	private Panel panel;
	/**
	 * Create the panel.
	 */
	public FriendsPanel(MainFrame mainFrame) {
		super(mainFrame);
		controller = new Controller();
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		panel = new Panel();
		panel.addComponentListener(new ComponentAdapter() {
        	@Override
        	public void componentResized(ComponentEvent arg0) {
        		regenerateLayoutSize();
        	}
        });
		add(panel);
	}
	
	public void paintConponent(Graphics graphics) {
		super.paintComponent(graphics);
		
		graphics.setColor(Color.GREEN);
		graphics.fillOval(100, 100, 10, 10);
	}
	
	public void generateFriendList(List<User> users) {
		Thread refresh = new Refresh(users, this);
		refresh.start();
		
		generateFriendsList(users);
	}

	public void generateFriendsList(List<User> users) {
		controller.sortByActiv(users);
		panel.removeAll();
		for (User user : users) {
			panel.add(new Friend(user, mainFrame));
		}
		repaint();
		revalidate();
	}

	private void regenerateLayoutSize() {
		int heightFriends = this.getHeight();
		Component[] components = panel.getComponents();
		MainPanel panel = (MainPanel) components[0];
		int heightFriend = panel.getHeight();
		
		int minRows = heightFriends / heightFriend;
		this.panel.setLayout(new GridLayout(Math.max(minRows, components.length), 0, 0, 0));
	}
}
