package gui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	protected String setColor(String text, Colors color) {
		text = clearText(text);
		switch (color) {
		case RED:
			return "<html><font color='red'>" + text + "</font></html>";
		case GREEN:
			return "<html><font color='green'>" + text + "</font></html>";
		case BLACK:
			return text;
		}
		return text;
	}
	
	private String clearText(String text) {
		String pattern = "(.*)('>)(.*)(</font>)(.*)";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(text);
		
		if(m.matches()) {
			String group = m.group(3);
			return group;
		} else {
			return text;
		}
	}

}
