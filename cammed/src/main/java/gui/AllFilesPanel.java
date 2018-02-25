package gui;
import java.awt.GridLayout;
import java.io.File;
import java.util.List;

import connections.DataHelper;
import database.memorydatabase.FileEntity;

public class AllFilesPanel extends MainPanel {

	private static final long serialVersionUID = 6464373460904425758L;

	public AllFilesPanel(MainFrame mainFrame) {
		super(mainFrame);
		setLayout(new GridLayout(25, 0, 0, 0));
	}
	 
	public void generateContent(List<FileEntity> files) {
		removeAll();
		for (FileEntity file : files) {
			String name = file.getName();
			if(name.endsWith(".jpg") || name.endsWith(".png")) {
				add(new SingleFilePanel(mainFrame, new File(DataHelper.FILE_PATH + file.getName())));
			}
		}
	}

}
