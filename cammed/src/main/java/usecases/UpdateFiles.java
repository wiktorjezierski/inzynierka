package usecases;

import java.io.File;
import java.util.List;

import connections.DataHelper;
import connections.rest.FileAccess;
import database.memorydatabase.DataBaseController;
import database.memorydatabase.FileEntity;

public class UpdateFiles extends Thread implements UseCase {
	
	public void run() {
		for (FileEntity fileEntity : readFiles()) {
			String name = fileEntity.getName();
			File file = new File(DataHelper.FILE_PATH + name);
			if(!file.exists()) {
				FileAccess.downloadFiles(name);
			}
		}
	}

	private List<FileEntity> readFiles() {
		DataBaseController mController = new DataBaseController();
		mController.openConnection();
		mController.beginTransaction();
		List<FileEntity> files = mController.findAll(FileEntity.class);
		mController.commitTransaction();
		return files;
	}

}
