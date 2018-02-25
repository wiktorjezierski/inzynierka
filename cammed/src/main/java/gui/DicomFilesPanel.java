package gui;

import java.awt.GridLayout;
import java.io.File;
import java.util.List;

import connections.DataHelper;
import database.memorydatabase.FileEntity;

public class DicomFilesPanel extends MainPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DicomFilesPanel(MainFrame mainFrame) {
		super(mainFrame);
		setLayout(new GridLayout(25, 0, 0, 0));
	}
	
	public void generateContent(List<FileEntity> files) {
		removeAll();
		for (FileEntity file : files) {
			String name = file.getName();
			if(name.endsWith(".dcm")) {
				add(new SingleFilePanel(mainFrame, new File(DataHelper.FILE_PATH + file.getName())));
			}
		}
	}
	
}
