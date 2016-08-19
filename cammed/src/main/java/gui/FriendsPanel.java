package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Panel;

import javax.swing.BoxLayout;

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
		add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		generateFriendList();
		
	}
	
	public void paintConponent(Graphics graphics) {
		super.paintComponent(graphics);
		
		graphics.setColor(Color.GREEN);
		graphics.fillOval(100, 100, 10, 10);
	}
	
	private void generateFriendList() {
		for (int i = 0; i < 20; i++) {
			panel.add(new Friend());
		}
	}
}