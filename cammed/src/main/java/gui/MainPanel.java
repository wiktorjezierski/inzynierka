package gui;

import javax.swing.JPanel;

public abstract class MainPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	protected MainFrame mainFrame;

	/**
	 * Create the panel.
	 */
	public MainPanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

}
