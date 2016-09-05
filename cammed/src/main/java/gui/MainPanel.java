package gui;

import javax.swing.JPanel;

import gui.helper.Controller;

public abstract class MainPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	protected MainFrame mainFrame;

	/**
	 * Create the panel.
	 */
	public MainPanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	
	protected String setColor(String text, Colors color) {
		text = Controller.clearText(text);
		return "<html><font color='"+ color.getValue() +"'>" + text + "</font></html>";
	}

}
