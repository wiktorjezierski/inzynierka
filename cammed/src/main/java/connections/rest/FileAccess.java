package connections.rest;

import java.io.File;
import java.util.UUID;

import javax.ws.rs.core.MultivaluedMap;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import connections.DataHelper;
import connections.rest.FileTO;

public class FileAccess {
	
	private Client client;
	private Gson gson;
	
	public FileAccess() {
		if (client == null || gson == null) {
			client = Client.create();
			gson = new Gson();
		}
	}
	
	public static boolean downloadFiles(String filename) {
		FileAccess fileAccess = new FileAccess();
		return fileAccess.sendGet(filename);
	}
	
	public static String sendFile(File file) {
		FileAccess fileAccess = new FileAccess();
		return fileAccess.sendPost(file);
	}

	private boolean sendGet(String filename) {
		try {
			WebResource webResource = client
					.resource(DataHelper.REQUEST_PATH + DataHelper.SUFFIX_DOWNLOAD + "?" + DataHelper.QUERY_PARAM_FILE_NAME + "=" + filename);	// docelowo to przekazac jako argument

			ClientResponse response = webResource.get(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			File output = response.getEntity(File.class);
			MultivaluedMap<String, String> headers = response.getHeaders();
			String value = headers.getFirst(DataHelper.HEADER_PARAM_FILE_NAME);
			
			output.renameTo(new File(DataHelper.FILE_PATH + value));
			
			System.out.println("Output from Server .... \n");
			System.out.println(output);
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private String sendPost(File file) {
		try {
			WebResource webResource = client.resource(DataHelper.REQUEST_PATH + DataHelper.SUFFIX_UPLOAD);
			
			FileTO fileTO = new FileTO(file, UUID.randomUUID().toString());
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
