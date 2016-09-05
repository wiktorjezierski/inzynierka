package gui.helper;

import java.io.IOException;
import java.util.List;

import actions.RefreshFriendsList;
import actions.Response;
import connections.Client;
import connections.DataHelper;
import database.User;
import gui.FriendsPanel;

public class Refresh extends Thread {
	
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
		} catch (IOException | ClassNotFoundException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}