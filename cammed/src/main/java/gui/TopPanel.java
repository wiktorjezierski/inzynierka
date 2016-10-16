package gui;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import gui.helper.Controller;

public class TopPanel extends MainPanel {

	private static final long serialVersionUID = 1L;

	private JButton btnWyloguj;
	/**
	 * Create the panel.
	 */
	public TopPanel(MainFrame mainFrame) {
		super(mainFrame);
		setLayout(new BorderLayout(0, 0));
		
		btnWyloguj = new JButton("Wyloguj");
		btnWyloguj.addMouseListener(mouseEvents());
		add(btnWyloguj, BorderLayout.EAST);
	}
	
	private MouseAdapter mouseEvents() {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					Controller.signOut();
					mainFrame.displayJPanel(Panels.LOGIN);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
	}

}
