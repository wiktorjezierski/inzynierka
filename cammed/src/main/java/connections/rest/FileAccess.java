package connections.rest;

import java.io.File;

import javax.ws.rs.core.MultivaluedMap;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import connections.DataHelper;

public class FileAccess {
	
	private Client client;
	private Gson gson;
	
	public FileAccess() {
		if (client == null || gson == null) {
			client = Client.create();
			gson = new Gson();
		}
	}
	
	public static boolean downloadFiles(String fileUuid, String fileName) {
		FileAccess fileAccess = new FileAccess();
		return fileAccess.sendGet(fileUuid, fileName);
	}
	
	public static String sendFile(File file, String fileUUID) {
		FileAccess fileAccess = new FileAccess();
		return fileAccess.sendPost(file, fileUUID);
	}

	private boolean sendGet(String fileUuid, String newFileName) {
		try {
			WebResource webResource = client
					.resource(DataHelper.REQUEST_PATH + DataHelper.SUFFIX_DOWNLOAD + "?" + DataHelper.QUERY_PARAM_FILE_NAME + "=" + fileUuid);	// docelowo to przekazac jako argument

			ClientResponse response = webResource.get(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			File output = response.getEntity(File.class);
			MultivaluedMap<String, String> headers = response.getHeaders();
//			String value = headers.getFirst(DataHelper.HEADER_PARAM_FILE_NAME);
			
			output.renameTo(new File(DataHelper.FILE_PATH + newFileName));
			
			System.out.println("Output from Server .... \n");
			System.out.println(output);
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private String sendPost(File file, String fileUUID) {
		try {
			WebResource webResource = client.resource(DataHelper.REQUEST_PATH + DataHelper.SUFFIX_UPLOAD);
			
			FileTO fileTO = new FileTO(file, fileUUID);
			String input = gson.toJson(fileTO);
			
			ClientResponse response = webResource.type("application/json").post(ClientResponse.class, input);

			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			System.out.println("Output from Server .... \n");
			String output = response.getEntity(String.class);
			System.out.println(output);

			return fileTO.getFileName();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
