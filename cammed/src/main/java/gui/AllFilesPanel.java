package gui;
import java.io.File;
import java.util.List;

import javax.swing.BoxLayout;

import connections.DataHelper;
import database.User;
import database.memorydatabase.FileEntity;
import gui.helper.Controller;
import java.awt.GridLayout;

public class AllFilesPanel extends MainPanel {

	private static final long serialVersionUID = 6464373460904425758L;

	public AllFilesPanel(MainFrame mainFrame) {
		super(mainFrame);
		setLayout(new GridLayout(25, 0, 0, 0));
		
	}
	 
	public void generateContent(User user) {
		Controller controller = new Controller();
		List<FileEntity> fileEntities = controller.readFiles(user);
		
		removeAll();
		for (FileEntity fileEntity : fileEntities) {
			add(new SingleFilePanel(mainFrame, new File(DataHelper.FILE_PATH + fileEntity.getName())));
		}
	}

}
