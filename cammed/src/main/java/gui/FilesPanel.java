package gui;
import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JTabbedPane;

import database.User;
import database.memorydatabase.FileEntity;
import gui.helper.Controller;

public class FilesPanel extends MainPanel {

	private static final String ALL_FILES = "All files";
	private static final String DICOM = "DICOM";
	private static final long serialVersionUID = -6212995464528143641L;
	
	private Controller controller;
	
	private AllFilesPanel allFiles;
	private DicomFilesPanel dicom;

	public FilesPanel(MainFrame mainFrame) {
		super(mainFrame);
		controller = new Controller();
		setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
		add(tabbedPane);
		
		allFiles = new AllFilesPanel(mainFrame);
		tabbedPane.addTab(ALL_FILES, null, allFiles, null);
		
		dicom = new DicomFilesPanel(mainFrame);
		tabbedPane.addTab(DICOM, null, dicom, null);
	}
	
	public void generateContent(User user) {
		List<FileEntity> files = controller.readFiles(user);
		allFiles.generateContent(files);
		dicom.generateContent(files);
	}
}
