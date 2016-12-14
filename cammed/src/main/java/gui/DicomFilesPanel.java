package gui;
import java.awt.GridLayout;
import java.io.File;
import java.util.List;

import connections.DataHelper;
import database.User;
import database.memorydatabase.FileEntity;
import gui.helper.Controller;

public class DicomFilesPanel extends MainPanel {

	private static final String DCM = ".dcm";
	
	private static final long serialVersionUID = 6464373460904425758L;

	public DicomFilesPanel(MainFrame mainFrame) {
		super(mainFrame);
		setLayout(new GridLayout(25, 0, 0, 0));
		
	}
	 
	public void generateContent(User user) {
		Controller controller = new Controller();
		List<FileEntity> fileEntities = controller.readFiles(user);
		
		removeAll();
		for (FileEntity fileEntity : fileEntities) {
			String fileName = fileEntity.getName();
			
			if(fileName.endsWith(DCM)) {
				add(new SingleFilePanel(mainFrame, new File(DataHelper.FILE_PATH + fileName)));
			}
		}
	}

}
