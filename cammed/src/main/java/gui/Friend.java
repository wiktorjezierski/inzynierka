package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import actions.CallAction;
import actions.Response;
import connections.Client;
import database.User;
import usecases.EstablishConnectionUC;
import usecases.UseCase;

public class Friend extends MainPanel {

	private static final long serialVersionUID = 1L;
	private User user;
	
	private JLabel name;
	private JButton call;

	/**
	 * Create the panel.
	 */
	public Friend(User userLocal, MainFrame mainFrame) {
		super(mainFrame);
		this.user = userLocal;
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		add(horizontalStrut);

		JLabel lblImage = new JLabel("");
		lblImage.setIcon(new ImageIcon("C:\\Projekty\\inzynierka\\cammed\\icons\\DefaultUserIcon.png"));
		add(lblImage);

		name = new JLabel("nick");
		name.setText(" " + user.getImie() + " " + user.getNazwisko() + " ");
		name.addMouseListener(showDetails());
		name.addMouseListener(mouseActions());
		repaint();
		add(name);
		
		call = new JButton("");

		call.addMouseListener(mouseEvents);
		call.setIcon(new ImageIcon("C:\\Projekty\\inzynierka\\cammed\\icons\\call.png"));
		add(call);

	}

	private MouseAdapter mouseActions() {
		return new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				name.setText(setColor(name.getText(), Colors.GREEN));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				name.setText(setColor(name.getText(), Colors.BLACK));
			}
		};
	}
	
	MouseAdapter mouseEvents = new MouseAdapter() {	// przeniesc to do oddzielnej klasy
		@Override
		public void mouseClicked(MouseEvent arg0) {
			try {
				CallAction callAction = new CallAction(user.getUuid());

				Client client = Client.connectWithMainSerwer();
				client.writeObject(callAction);
				Response response = client.readObject();
				
				UseCase establishConnection = new EstablishConnectionUC(response.getValue(), response.getDeviceType());
				establishConnection.execute();
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	};

	private MouseAdapter showDetails() {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
//				JOptionPane.showMessageDialog(null, user.toString());
				UserDescription userDescription = (UserDescription) mainFrame.getPanel(MainFrame.USER_DETAILS);
				userDescription.setValue(user);
				
				mainFrame.changeCenterPanel(MainFrame.USER_DETAILS);
			}
		};
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
		graphics.fillOval(10, 10, 10, 10);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
