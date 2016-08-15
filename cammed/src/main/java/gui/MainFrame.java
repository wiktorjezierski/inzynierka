package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame {
	
	public static final int LOGIN = 0;
	public static final int BOTTOM = 1;
	public static final int TOP = 2;
	public static final int FRIENDS = 3;
	public static final int SIGN_UP = 4;
	
	private static final long serialVersionUID = 1L;
	
	private Map<Integer, JPanel> maping;
	
	private JPanel contentPane;
//	private JPanel loginPanel;

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
		createJPanels();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
//		loginPanel = new LoginPanel(this);
		contentPane.add(maping.get(LOGIN), BorderLayout.CENTER);
		
	}
	
	public void generateMainGuiDesign() {
		JPanel loginPanel = maping.get(LOGIN);
		contentPane.remove(loginPanel);
		loginPanel.setVisible(false);
		contentPane.add(maping.get(TOP), BorderLayout.NORTH);
		contentPane.add(maping.get(BOTTOM), BorderLayout.SOUTH);
		this.repaint();
	}
	
	public void displayJPanel(int oldPanel, int newPanel, String position) {
		JPanel jPanelOld = maping.get(oldPanel);
		jPanelOld.setVisible(false);
		
		JPanel panel = maping.get(newPanel);
		contentPane.add(panel, position);
		panel.setVisible(true);
		repaint();
	}
	
	private void createJPanels() {
		maping = new HashMap<Integer, JPanel>();
		
		maping.put(LOGIN, new LoginPanel(this));
		maping.put(BOTTOM, new BottonPanel(this));
		maping.put(TOP, new TopPanel(this));
		maping.put(FRIENDS, new FriendsPanel(this));
		maping.put(SIGN_UP, new SignUpPanel(this));
	}

}