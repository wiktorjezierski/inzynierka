package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.UUID;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import database.User;

public class Friend extends JPanel {

	private static final long serialVersionUID = 1L;
	private UUID uuid;
	private User user;

	/**
	 * Create the panel.
	 */
	public Friend(User user) {
		this.user = user;
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		add(horizontalStrut);

		JLabel lblImage = new JLabel("image");
		add(lblImage);

		JButton btnNick = new JButton("nick");
		btnNick.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				repaint();
			}
		});
		repaint();
		add(btnNick);

	}

	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);

		if (user.getStatus()) {
			graphics.setColor(Color.GREEN);
		} else {
			graphics.setColor(Color.RED);
		}
		graphics.fillOval(0, 15, 10, 10);
	}

}
