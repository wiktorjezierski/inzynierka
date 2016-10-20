package connections.messaging;

/**
 * Created by pdyjak on 18.10.16.
 */
public class DicomReadyMessage {
    /**
     * Destination device id
     */
    public final String to;

    public final MessageData data;

    public DicomReadyMessage(String to, MessageData messageData) {
        this.to = to;
        this.data = messageData;
    }
}
