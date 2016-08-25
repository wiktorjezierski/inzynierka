package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.ScrollPane;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;

import database.User;

public class MainFrame extends JFrame {
	
	public static final int LOGIN = 0;
	public static final int BOTTOM = 1;
	public static final int TOP = 2;
	public static final int FRIENDS = 3;
	public static final int SIGN_UP = 4;
	public static final int USER_DETAILS = 5;
	
	private static final long serialVersionUID = 1L;
	
	private Map<Integer, JPanel> maping;
	
	private JPanel contentPane;
//	private JPanel loginPanel;
	
	private JSplitPane splitPane_1;

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
		contentPane.setLayout(new BorderLayout(5, 5));
		
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

	public void generateMainGuiDesign(List<User> users) {
		JPanel loginPanel = maping.get(LOGIN);
		contentPane.remove(loginPanel);
		
		FriendsPanel friendsPanel = (FriendsPanel) maping.get(FRIENDS);
		friendsPanel.generateFriendList(users);
		
		createContent();
		maximizeScreen();
		repaint();
	}
	
	private void createContent() {
		JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.setLayout(new BorderLayout(5, 5));
        JPanel centerPanel = new JPanel();
        GridBagLayout gbl_centerPanel = new GridBagLayout();
        gbl_centerPanel.rowWeights = new double[]{1.0};
        gbl_centerPanel.columnWeights = new double[]{1.0};
        centerPanel.setLayout(gbl_centerPanel);        

        JScrollPane scrollpane = new JScrollPane();
        scrollpane.setEnabled(false);
        
        JSplitPane splitPane = new JSplitPane();
        GridBagConstraints gbc_splitPane = new GridBagConstraints();
        gbc_splitPane.insets = new Insets(0, 0, 5, 0);
        gbc_splitPane.fill = GridBagConstraints.BOTH;
        gbc_splitPane.gridx = 0;
        gbc_splitPane.gridy = 0;
        centerPanel.add(splitPane, gbc_splitPane);
        
        splitPane_1 = new JSplitPane();
        splitPane.setRightComponent(splitPane_1);
        
        JPanel srodek = new JPanel();
        srodek.addComponentListener(new ComponentAdapter() {
        	@Override
        	public void componentResized(ComponentEvent arg0) {
        		srodek.setMinimumSize(new Dimension((int) (contentPane.getWidth() * 0.55), 1000));
        	}
        });
        splitPane_1.setLeftComponent(srodek);
        
        JPanel prawa = new JPanel();
        prawa.setMaximumSize(new Dimension(100, 1080));
        splitPane_1.setRightComponent(prawa);
        
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setMinimumSize(new Dimension(250, 100));
        splitPane.setLeftComponent(scrollPane);
        
        scrollPane.add(maping.get(FRIENDS));
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(maping.get(TOP), BorderLayout.NORTH);
        panel.add(maping.get(BOTTOM), BorderLayout.SOUTH);
        getContentPane().add(panel);
        setVisible(true);
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
	
	
	
	public void changeCenterPanel(int newPanel) {
		Component leftComponent = splitPane_1.getLeftComponent();
		splitPane_1.remove(leftComponent);
		
		MainPanel panel = getPanel(newPanel);
		splitPane_1.setLeftComponent(panel);
	}
	
	private void createJPanels() {
		maping = new HashMap<Integer, JPanel>();
		
		maping.put(LOGIN, new LoginPanel(this));
		maping.put(BOTTOM, new BottonPanel(this));
		maping.put(TOP, new TopPanel(this));
		maping.put(FRIENDS, new FriendsPanel(this));
		maping.put(SIGN_UP, new SignUpPanel(this));
		maping.put(USER_DETAILS, new UserDescription(this));
	}
	
	public MainPanel getPanel(int name) {
		return (MainPanel) maping.get(name);
	}
}