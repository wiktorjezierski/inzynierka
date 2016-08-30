package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.BoxLayout;

import actions.RefreshFriendsList;
import actions.Response;
import connections.Client;
import connections.DataHelper;
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
		new Refresh(users, this).start();
		generateFriendsList(users);
	}

	protected void generateFriendsList(List<User> users) {
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

class Refresh extends Thread {
	
	private List<User> users;
	private FriendsPanel friendsPanel;

	public Refresh(List<User> users, FriendsPanel friendsPanel) {
		super();
		this.friendsPanel = friendsPanel;
		this.users = users;
	}
	
	public void run() {
		try {
			RefreshFriendsList refreshFriendsList = new RefreshFriendsList(users);
			Client connection = Client.connectWithMainSerwer();
			while (true) {
				Thread.sleep(DataHelper.TIME_DELAY);
				connection.writeObject(refreshFriendsList);
				Response response = (Response) connection.readObject();
				friendsPanel.generateFriendsList(response.getUsers());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}



