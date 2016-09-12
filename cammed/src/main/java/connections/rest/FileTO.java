package connections.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Pattern;

public class FileTO {
	
	private byte [] array;
	private String fileName;
	
	public FileTO(File file, String fileName) throws FileNotFoundException {
		InputStream inputStream;
		try {
			inputStream = new FileInputStream(file);
			array = new byte[(int) file.length()];
			inputStream.read(array);
			
			this.fileName = generateFilename(file, fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String generateFilename(File file, String newName) {
		String oldName = file.getName();
		String[] split = oldName.split(Pattern.quote("."));
		String suffix = split[split.length - 1];
		return newName + "." + suffix;
	}

	public byte[] getArray() {
		return array;
	}
	public String getFileName() {
		return fileName;
	}

}
