package connections;

public interface DataHelper {

	public final static String MAIN_SERVER_ADDRESS = "192.168.0.4";
	public final static int MAIN_SERVER_PORT = 6066;
	
	public final static int VIDEO_PORT = 6060;
	public final static int AUDIO_PORT = 6061;
	
	public final static int AUDIO_CHUNK_SIZE = 4096;
	
	public final static int TIME_DELAY = 10000;
	
	public static final String REQUEST_PATH = "http://localhost:8080/cammedWebservice/rest/json/";
	public static final String SUFFIX_UPLOAD = "upFiles";
	public static final String SUFFIX_DOWNLOAD = "files";
	public static final String HEADER_PARAM_FILE_NAME = "fileName";
	public static final String QUERY_PARAM_FILE_NAME = "fileName";
	public static final String FILE_PATH_DESTINATION = "C:\\Projekty2TEMP\\cammedFTP\\";
}
