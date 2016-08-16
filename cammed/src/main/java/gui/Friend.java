package gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;

public class Friend extends JPanel {

	private static final long serialVersionUID = 1L;
	private boolean check = false;

	/**
	 * Create the panel.
	 */
	public Friend() {
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		add(horizontalStrut);

		JLabel lblImage = new JLabel("image");
		add(lblImage);

		JButton btnNick = new JButton("nick");
		btnNick.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				check = true;
				repaint();
			}
		});
		add(btnNick);

	}

	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);

		if (check) {
			graphics.setColor(Color.RED);
			graphics.fillOval(0, 15, 10, 10);
		}
	}

}
