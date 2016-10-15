package gui;
import javax.swing.JTabbedPane;

import database.User;

import javax.swing.JPanel;
import java.awt.BorderLayout;

public class FilesPanel extends MainPanel {

	private static final String ALL_FILES = "All files";
	private static final String DICOM = "DICOM";
	private static final long serialVersionUID = -6212995464528143641L;
	
	private AllFilesPanel files;
	private JPanel dicom;

	public FilesPanel(MainFrame mainFrame) {
		super(mainFrame);
		setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
		add(tabbedPane);
		
		files = new AllFilesPanel(mainFrame);
		tabbedPane.addTab(ALL_FILES, null, files, null);
		
		dicom = new JPanel();
		tabbedPane.addTab(DICOM, null, dicom, null);
	}
	
	public void generateContent(User user) {
		files.generateContent(user);
	}
}
