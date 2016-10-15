package gui;
import java.io.File;
import java.util.List;

import javax.swing.BoxLayout;

import connections.DataHelper;
import database.User;
import database.memorydatabase.FileEntity;
import gui.helper.Controller;

public class AllFilesPanel extends MainPanel {

	private static final long serialVersionUID = 6464373460904425758L;

	public AllFilesPanel(MainFrame mainFrame) {
		super(mainFrame);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
	}
	 
	public void generateContent(User user) {
		Controller controller = new Controller();
		List<FileEntity> fileEntities = controller.readFiles(user);
		
		for (FileEntity fileEntity : fileEntities) {
			add(new SingleFilePanel(mainFrame, new File(DataHelper.FILE_PATH + fileEntity.getName())));
		}
	}

}
