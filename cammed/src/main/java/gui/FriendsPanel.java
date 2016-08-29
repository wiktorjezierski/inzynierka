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

public class FriendsPanel extends MainPanel {

	private static final long serialVersionUID = 1L;
	
	private Panel panel;
	/**
	 * Create the panel.
	 */
	public FriendsPanel(MainFrame mainFrame) {
		super(mainFrame);
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
		sortByActiv(users);
		panel.removeAll();
		for (User user : users) {
			panel.add(new Friend(user, mainFrame));
		}
	}
	
	public void sortByActiv(List<User> users) {
		int i, j;
		for (i = 0; i < users.size(); i++) {
			if(!users.get(i).getStatus()){
				break;
			}
		}
		
		for (j = users.size() - 1; j >= 0; j--) {
			if(users.get(j).getStatus()){
				break;
			}
		}
		
		if(i < j) {
			User temp = users.get(i);
			users.set(i, users.get(j));
			users.set(j, temp);
			sortByActiv(users);
		} else {
			return;
		}
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