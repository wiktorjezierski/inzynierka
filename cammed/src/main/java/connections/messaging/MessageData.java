package connections.messaging;

/**
 * Created by pdyjak on 18.10.16.
 */
public class MessageData {
    /**
     * A message body, describing content under given url, for example:
     * "Jan Kowalski's head computed tomography"
     */
    public final String body;

    /**
     * A url pointing to a valid Dicom file.
     */
    public final String url;

    public MessageData(String body, String url) {
        this.body = body;
        this.url = url;
    }
}
