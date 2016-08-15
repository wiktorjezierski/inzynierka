package gui;
import javax.swing.JButton;
import java.awt.BorderLayout;

public class BottonPanel extends MainPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public BottonPanel(MainFrame mainFrame) {
		super(mainFrame);
		setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton = new JButton("New button");
		add(btnNewButton, BorderLayout.NORTH);
		
		JButton btnNewButton_1 = new JButton("New button");
		add(btnNewButton_1, BorderLayout.SOUTH);
	}
}
