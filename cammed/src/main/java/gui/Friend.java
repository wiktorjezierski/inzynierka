package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

import database.User;
import javax.swing.ImageIcon;

public class Friend extends MainPanel {

	private static final long serialVersionUID = 1L;
	private User user;
	
	private JButton name;
	private JButton call;

	/**
	 * Create the panel.
	 */
	public Friend(User user, MainFrame mainFrame) {
		super(mainFrame);
		this.user = user;
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		add(horizontalStrut);

		JLabel lblImage = new JLabel("image");
		add(lblImage);

		name = new JButton("nick");
		name.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				repaint();
			}
		});
		repaint();
		add(name);
		
		call = new JButton("");
		call.setIcon(new ImageIcon("C:\\Projekty\\inzynierka\\cammed\\icons\\call.png"));
		add(call);

	}

	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);

		if (user.getStatus()) {
			graphics.setColor(Color.GREEN);
			call.setEnabled(true);
		} else {
			graphics.setColor(Color.RED);
			call.setEnabled(false);
		}
		graphics.fillOval(0, 10, 10, 10);
		
		name.setText(user.getImie() + " " + user.getNazwisko());
		
	}

}
