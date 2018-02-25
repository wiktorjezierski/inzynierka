package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.ScrollPane;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import usecases.SignOutUC;
import usecases.UseCase;

public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private Map<Panels, JPanel> maping;
	
	private JPanel contentPane;
	
	private JSplitPane splitPane_1;

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		addWindowListener(frameAction());
		createJPanels();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Plik");
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_1 = new JMenu("Edit");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmSaveFile = new JMenuItem("Save file");
		mntmSaveFile.addMouseListener(saveFileAction());
		mnNewMenu_1.add(mntmSaveFile);
		
		JMenu mnNewMenu_2 = new JMenu("Help");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addMouseListener(keyAbout());

		mnNewMenu_2.add(mntmAbout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(5, 5));
		
		contentPane.add(maping.get(Panels.LOGIN), BorderLayout.CENTER);
		
	}

	private MouseAdapter saveFileAction() {
		return new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				PhotoEditorPanel photoEditorPanel = (PhotoEditorPanel) getPanel(Panels.PHOTO_EDITOR);
				photoEditorPanel.saveFile();
			}
		};
	}

	private WindowAdapter frameAction() {
		return new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				try {
					UseCase signOut = new SignOutUC();
					signOut.execute();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
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
		JPanel loginPanel = maping.get(Panels.LOGIN);
		contentPane.remove(loginPanel);
		
		FriendsPanel friendsPanel = (FriendsPanel) maping.get(Panels.FRIENDS);
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
        
        final JPanel srodek = new JPanel();
        srodek.addComponentListener(new ComponentAdapter() {
        	@Override
        	public void componentResized(ComponentEvent arg0) {
        		srodek.setMinimumSize(new Dimension((int) (contentPane.getWidth() * 0.55), 1000));
        	}
        });
        splitPane_1.setLeftComponent(srodek);
        
        MainPanel filesPanel = getPanel(Panels.FILES);
        splitPane_1.setRightComponent(filesPanel);
        
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setMinimumSize(new Dimension(250, 100));
        splitPane.setLeftComponent(scrollPane);
        
        scrollPane.add(maping.get(Panels.FRIENDS));
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(maping.get(Panels.TOP), BorderLayout.NORTH);
        panel.add(maping.get(Panels.BOTTOM), BorderLayout.SOUTH);
        getContentPane().add(panel);
        setVisible(true);
	}


	private void maximizeScreen() {
		this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
	}

	public void displayJPanel(Panels oldPanel, Panels newPanel, String position) {
		JPanel jPanelOld = maping.get(oldPanel);
		jPanelOld.setVisible(false);
		
		JPanel panel = maping.get(newPanel);
		contentPane.add(panel, position);
		panel.setVisible(true);
		repaint();
	}
	
	public void displayJPanel(Panels newPanel) {
		getContentPane().removeAll();
		getContentPane().add(getPanel(newPanel));
		repaint();
	}
	
	
	
	public void changeCenterPanel(Panels newPanel) {
		Component leftComponent = splitPane_1.getLeftComponent();
		splitPane_1.remove(leftComponent);
		
		MainPanel panel = getPanel(newPanel);
		splitPane_1.setLeftComponent(panel);
	}
	
	private void createJPanels() {
		maping = new HashMap<Panels, JPanel>();
		
		maping.put(Panels.LOGIN, new LoginPanel(this));
		maping.put(Panels.BOTTOM, new BottonPanel(this));
		maping.put(Panels.TOP, new TopPanel(this));
		maping.put(Panels.FRIENDS, new FriendsPanel(this));
		maping.put(Panels.SIGN_UP, new SignUpPanel(this));
		maping.put(Panels.USER_DETAILS, new UserDescription(this));
		maping.put(Panels.FILES, new FilesPanel(this));
		maping.put(Panels.PHOTO_EDITOR, new PhotoEditorPanel(this));
	}
	
	public MainPanel getPanel(Panels name) {
		return (MainPanel) maping.get(name);
	}
}