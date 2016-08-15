package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JPanel loginPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		loginPanel = new LoginPanel(this);
		contentPane.add(loginPanel, BorderLayout.CENTER);
		
	}
	
	public void generateMainGuiDesign() {
		contentPane.remove(loginPanel);
		loginPanel.setVisible(false);
		contentPane.add(new TopPanel(), BorderLayout.NORTH);
		contentPane.add(new BottonPanel(), BorderLayout.SOUTH);
		this.repaint();
	}
	
	public void displayJPanel(MainPanel oldPanel, MainPanel newPanel, String position) {
		oldPanel.setVisible(false);
		
		contentPane.add(newPanel, position);
		newPanel.setVisible(true);
		repaint();
	}

}
