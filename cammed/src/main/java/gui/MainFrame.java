package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
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
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Plik");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmJeden = new JMenuItem("jeden");
		mnNewMenu.add(mntmJeden);
		
		JMenuItem mntmDwa = new JMenuItem("dwa");
		mnNewMenu.add(mntmDwa);
		
		JMenu mnNewMenu_1 = new JMenu("Edit");
		menuBar.add(mnNewMenu_1);
		
		JMenu mnNewMenu_2 = new JMenu("Help");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addMouseListener(keyAbout());

		mnNewMenu_2.add(mntmAbout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		contentPane.add(maping.get(LOGIN), BorderLayout.CENTER);
		
	}

	private MouseAdapter keyAbout() {
		return new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "CamMed version 1.0 designed by Technical University od Wroclaw");
			}
		};
	}

	public void generateMainGuiDesign() {
		JPanel loginPanel = maping.get(LOGIN);
		contentPane.remove(loginPanel);
		loginPanel.setVisible(false);
		contentPane.add(maping.get(TOP), BorderLayout.NORTH);
		contentPane.add(maping.get(BOTTOM), BorderLayout.SOUTH);
		maximizeScreen();
		this.repaint();
	}
	
	private void maximizeScreen() {
		this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
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