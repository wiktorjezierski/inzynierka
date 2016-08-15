package gui;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;

public class TopPanel extends MainPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public TopPanel(MainFrame mainFrame) {
		super(mainFrame);
		setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton = new JButton("New button");
		add(btnNewButton, BorderLayout.NORTH);
		
		JButton button = new JButton("New button");
		add(button, BorderLayout.SOUTH);
	}

}
