package connections.messaging;


import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by pdyjak on 18.10.16.
 */
public class MessageSender {
    private static final String POST = "POST";
    private static final String AUTHORIZATION = "Authorization";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String JSON_CONTENT_TYPE = "application/json";
    private static final String FIREBASE_ADDRESS = "https://fcm.googleapis.com/fcm/send";
    private static final String FIREBASE_SERVER_KEY = "key=AIzaSyDje-rGIihZEjbLMbSC-UU4lqsXDwLre2U";

    private final DeviceIdResolver mDeviceIdResolver;

    public MessageSender(DeviceIdResolver deviceIdResolver) {
        if (deviceIdResolver == null) {
            throw new IllegalArgumentException("DeviceIdResolved cannot be null");
        }
        mDeviceIdResolver = deviceIdResolver;
    }

    public boolean sendDicomSharedMessage(String destinationUserUuid, String decription, String url) {
        String userDeviceId = mDeviceIdResolver.getDeviceIdFor(destinationUserUuid);
        DicomReadyMessage msg = new DicomReadyMessage(userDeviceId,
                new MessageData(decription, url));
        String json = new Gson().toJson(msg);

        HttpURLConnection connection = null;
        try {
            URL url1 = new URL(FIREBASE_ADDRESS);
            connection = (HttpURLConnection) url1.openConnection();
            connection.setRequestMethod(POST);
            connection.setRequestProperty(CONTENT_TYPE, JSON_CONTENT_TYPE);
            connection.setRequestProperty(AUTHORIZATION, FIREBASE_SERVER_KEY);

            connection.setUseCaches(false);
            connection.setDoOutput(true);

            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(json);
            outputStream.close();

            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
            }
            rd.close();
            System.out.println("Response: " + response.toString());
            return true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) connection.disconnect();
        }
        return false;
    }
}
